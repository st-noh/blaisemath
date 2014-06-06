/*
 * Copyright 2014 Elisha.
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

package org.blaise.style;

/*
 * #%L
 * BlaiseGraphics
 * --
 * Copyright (C) 2009 - 2014 Elisha Peterson
 * --
 * Licensed under the Apache License, Version 2.0.
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.awt.Color;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Elisha
 */
public class ColorUtilsTest {
    
    public ColorUtilsTest() {
    }

    @Test
    public void testLighterThan() {
        System.out.println("lighterThan");
        assertEquals(Color.white, ColorUtils.lighterThan(Color.white));
        assertEquals(Color.darkGray, ColorUtils.lighterThan(Color.black));
        assertEquals(new Color(114,64,64,128), ColorUtils.lighterThan(new Color(50,0,0,128)));
    }

    @Test
    public void testBlanderThan() {
        System.out.println("blanderThan");
        assertEquals(Color.white, ColorUtils.blanderThan(Color.white));
        assertEquals(Color.black, ColorUtils.blanderThan(Color.black));
        assertEquals(new Color(50,25,25,128), ColorUtils.blanderThan(new Color(50,0,0,128)));
    }

    @Test
    public void testApplyHints() {
        System.out.println("applyHints");
        StyleHintSet set = new StyleHintSet();
        assertEquals(new Color(50,0,0,128), ColorUtils.applyHints(new Color(50,0,0,128), set));
        set.add(StyleHints.FADED_HINT);
        assertEquals(new Color(50,25,25,128), ColorUtils.applyHints(new Color(50,0,0,128), set));
        set.remove(StyleHints.FADED_HINT);
        set.add(StyleHints.HIDDEN_HINT);
        assertEquals(new Color(50,0,0,128), ColorUtils.applyHints(new Color(50,0,0,128), set));
        set.remove(StyleHints.HIDDEN_HINT);
        set.add(StyleHints.HIGHLIGHT_HINT);
        assertEquals(new Color(114,64,64,128), ColorUtils.applyHints(new Color(50,0,0,128), set));
        set.remove(StyleHints.HIGHLIGHT_HINT);
        set.add(StyleHints.OUTLINE_HINT);
        assertEquals(new Color(50,0,0,128), ColorUtils.applyHints(new Color(50,0,0,128), set));
        set.remove(StyleHints.OUTLINE_HINT);
        set.add(StyleHints.SELECTED_HINT);
        assertEquals(new Color(114,64,64,128), ColorUtils.applyHints(new Color(50,0,0,128), set));
    }
    
}
