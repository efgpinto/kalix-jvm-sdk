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

package com.akkaserverless.javasdk.impl

import com.akkaserverless.javasdk.PassivationStrategy

import java.time.Duration

private[akkaserverless] case class Timeout private (duration: Option[Duration]) extends PassivationStrategy {

  def this() {
    this(None) // use the timeout from the default or customized settings
  }

  def this(duration: Duration) {
    this(Some(duration))
  }
}