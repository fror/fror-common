/*
 * Copyright 2015 Olivier Grégoire <https://github.com/ogregoire>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.fror.common.resource2;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import java.io.IOException;

/**
 *
 * @author Olivier Grégoire <https://github.com/ogregoire>
 */
public class ResourceTest {

  @Test
  public void shouldCacheInstanceAndReturnIt() throws IOException {
    ResourceLoader<String> loader = mock(ResourceLoader.class);
    String abc = new String("abc"); // Force a specific address to avoid javac optimization
    when(loader.uncheckedLoad(any())).thenReturn(abc);
    when(loader.load(any())).thenReturn(abc);

    Resource<String> resource = new Resource(null, loader);

    assertThat(resource.get(), is(sameInstance(abc)));
    assertThat(resource.get(), is(sameInstance(abc)));

    verify(loader, times(1)).uncheckedLoad(any());
  }
}
