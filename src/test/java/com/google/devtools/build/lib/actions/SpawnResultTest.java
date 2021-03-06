// Copyright 2018 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.devtools.build.lib.actions;

import static com.google.common.truth.Truth.assertThat;

import java.time.Duration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Testing common SpawnResult features
 */
@RunWith(JUnit4.class)
public final class SpawnResultTest {

  @Test
  public void getTimeoutMessage() {
    SpawnResult r =
        new SpawnResult.Builder()
            .setStatus(SpawnResult.Status.TIMEOUT)
            .setWallTime(Duration.ofSeconds(5))
            .setExitCode(1)
            .build();
    assertThat(r.getDetailMessage("", "", false, false))
        .contains("(failed due to timeout after 5.00 seconds.)");
  }

  @Test
  public void getTimeoutMessageNoTime() {
    SpawnResult r =
        new SpawnResult.Builder().setStatus(SpawnResult.Status.TIMEOUT).setExitCode(1).build();
    assertThat(r.getDetailMessage("", "", false, false))
        .contains("(failed due to timeout.)");
  }
}
