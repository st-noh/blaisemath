/*
 * StyleContext.java
 * Created Jan 22, 2011
 */
package com.googlecode.blaisemath.style.context;

import com.googlecode.blaisemath.style.Style;
import javax.annotation.Nullable;

/*
 * #%L
 * BlaiseGraphics
 * --
 * Copyright (C) 2009 - 2014 Elisha Peterson
 * --
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
 * #L%
 */

/**
 * Delegates all styles to a parent style. This class is designed to be overridden
 * so that subclasses only need to implement overrides for a few styles.
 * 
 * @param <S> type of object to be styled
 * 
 * @author elisha
 */
public abstract class StyleContextDecorated<S> implements StyleContext<S> {
    
    @Nullable
    protected final StyleContext<? super S> parent;

    public StyleContextDecorated() {
        this(StyleContextBasic.getInstance());
    }

    public StyleContextDecorated(@Nullable StyleContext<? super S> parent) {
        this.parent = parent;
    }

    /**
     * Return the parent factory used for delegation
     * @return parent
     */
    @Nullable
    public StyleContext<? super S> getParentContext() {
        return parent;
    }

    public <T extends Style> T getStyle(Class<T> cls, T pref, S src, StyleHintSet hints) {
        return parent == null ? null : parent.getStyle(cls, pref, src, hints);
    }
    
}