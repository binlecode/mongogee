/*******************************************************************************
 *  Copyright 2017 Bin Le
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/

package grails.plugin.mongogee;

import java.io.Serializable;
import java.util.Comparator;

import static org.springframework.util.StringUtils.hasText;

/**
 * Sort ChangeLogs by 'order' value or class name (if no 'order' is set)
 *
 * @author lstolowski
 * @since 2014-09-17
 */
public class ChangeLogComparator implements Comparator<Class<?>>, Serializable {
    @Override
    public int compare(Class<?> o1, Class<?> o2) {
        ChangeLog c1 = o1.getAnnotation(ChangeLog.class);
        ChangeLog c2 = o2.getAnnotation(ChangeLog.class);

        String val1 = !(hasText(c1.order())) ? o1.getCanonicalName() : c1.order();
        String val2 = !(hasText(c2.order())) ? o2.getCanonicalName() : c2.order();

        if (val1 == null && val2 == null) {
            return 0;
        } else if (val1 == null) {
            return -1;
        } else if (val2 == null) {
            return 1;
        }

        return val1.compareTo(val2);
    }
}
