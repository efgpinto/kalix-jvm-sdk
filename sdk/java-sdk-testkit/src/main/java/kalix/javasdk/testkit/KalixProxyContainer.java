/*
 * Copyright 2021 Lightbend Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kalix.javasdk.testkit;

import org.testcontainers.Testcontainers;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

/** Docker test container of Kalix proxy for local development and testing. */
public class KalixProxyContainer extends GenericContainer<KalixProxyContainer> {

  /** Default Kalix proxy image for local testing. */
  public static final String DEFAULT_PROXY_IMAGE = BuildInfo.proxyImage();

  /** Default Kalix proxy version, compatible with this version of the SDK and testkit. */
  public static final String DEFAULT_PROXY_VERSION = BuildInfo.proxyVersion();

  /** Default Testcontainers DockerImageName for the Kalix proxy. */
  public static final DockerImageName DEFAULT_PROXY_IMAGE_NAME =
      DockerImageName.parse(DEFAULT_PROXY_IMAGE).withTag(DEFAULT_PROXY_VERSION);

  /** Default proxy port (9000). */
  public static final int DEFAULT_PROXY_PORT = 9000;

  /** Default user function port (8080). */
  public static final int DEFAULT_USER_FUNCTION_PORT = 8080;

  /** Default local port where the Google Pub/Sub emulator is available (8085). */
  public static final int DEFAULT_GOOGLE_PUBSUB_PORT = 8085;

  private final int userFunctionPort;
  private final int googlePubSubPort;

  public KalixProxyContainer() {
    this(DEFAULT_USER_FUNCTION_PORT);
  }

  public KalixProxyContainer(final int userFunctionPort) {
    this(DEFAULT_PROXY_IMAGE_NAME, userFunctionPort, DEFAULT_GOOGLE_PUBSUB_PORT);
  }

  public KalixProxyContainer(final int userFunctionPort, int googlePubSubPort) {
    this(DEFAULT_PROXY_IMAGE_NAME, userFunctionPort, googlePubSubPort);
  }

  public KalixProxyContainer(
      final DockerImageName dockerImageName,
      final int userFunctionPort,
      final int googlePubSubPort) {
    super(dockerImageName);
    this.userFunctionPort = userFunctionPort;
    this.googlePubSubPort = googlePubSubPort;
    withExposedPorts(DEFAULT_PROXY_PORT);
    withEnv("USER_FUNCTION_HOST", "host.testcontainers.internal");
    withEnv("USER_FUNCTION_PORT", String.valueOf(userFunctionPort));
    withEnv("HTTP_PORT", String.valueOf(DEFAULT_PROXY_PORT));
    // connect to local Google Pub/Sub emulator
    withEnv("EVENTING_SUPPORT", "google-pubsub-emulator");
    withEnv("PUBSUB_EMULATOR_HOST", "host.testcontainers.internal");
    withEnv("PUBSUB_EMULATOR_PORT", String.valueOf(googlePubSubPort));
    if ("false".equals(System.getenv("VERSION_CHECK_ON_STARTUP"))) {
      withEnv("VERSION_CHECK_ON_STARTUP", "false");
    }
    waitingFor(Wait.forLogMessage(".*gRPC proxy started.*", 1));
  }

  @Override
  public void start() {
    Testcontainers.exposeHostPorts(userFunctionPort);
    Testcontainers.exposeHostPorts(googlePubSubPort);
    super.start();
    // Debug tooling: pass the Proxy logs into the client SLF4J
    // Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(LoggerFactory.getLogger("proxy-logs"));
    // followOutput(logConsumer);
  }

  /**
   * Get the mapped port for the Kalix proxy container.
   *
   * @return port for the local Kalix service
   */
  public int getProxyPort() {
    return getMappedPort(DEFAULT_PROXY_PORT);
  }
}
