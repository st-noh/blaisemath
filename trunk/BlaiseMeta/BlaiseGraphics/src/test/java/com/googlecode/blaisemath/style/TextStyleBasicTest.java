/*
 * Copyright 2014 Elisha Peterson.
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

package com.googlecode.blaisemath.style;

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

import com.googlecode.blaisemath.style.TextStyleBasic;
import com.googlecode.blaisemath.style.Anchor;
import com.googlecode.blaisemath.style.svg.StyleUtilsSVG;
import java.awt.Color;
import java.awt.Font;
import java.beans.IntrospectionException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Elisha Peterson
 */
public class TextStyleBasicTest {

    @Test
    public void testSerialization() throws IntrospectionException {
        System.out.println("svgSerialization");
        TextStyleBasic sty = new TextStyleBasic().fill(Color.black).font(new Font("Dialog",10,Font.ITALIC)).fontSize(12f).offset(2,1).textAnchor(Anchor.WEST);
        assertEquals("fill:#000000; font-size:12.0", StyleUtilsSVG.convertStyleToSVG(sty));
    }
    
}