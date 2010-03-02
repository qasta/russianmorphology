/**
 * Copyright 2009 Alexander Kuznetsov
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
package org.apache.lucene.morphology.russian;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;


public class RussianAnalayzerTest {

    @Test
    public void shoudGiveCorretWords() throws IOException {
        InputStream stream = this.getClass().getResourceAsStream("/org/apache/lucene/morphology/russian/russian-analayzer-answer.txt");
        BufferedReader breader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        String[] strings = breader.readLine().replaceAll(" +", " ").trim().split(" ");
        HashSet<String> answer = new HashSet<String>(Arrays.asList(strings));
        stream.close();

        RussianAnalayzer morphlogyAnalayzer = new RussianAnalayzer();
        stream = this.getClass().getResourceAsStream("/org/apache/lucene/morphology/russian/russian-analayzer-data.txt");

        InputStreamReader reader = new InputStreamReader(stream, "UTF-8");

        TokenStream tokenStream = morphlogyAnalayzer.tokenStream(null, reader);
        HashSet<String> result = new HashSet<String>();
        while (tokenStream.incrementToken()) {
            TermAttribute attribute1 = tokenStream.getAttribute(TermAttribute.class);
            result.add(attribute1.term());
        }

        stream.close();

        assertThat(result, equalTo(answer));
    }
}
