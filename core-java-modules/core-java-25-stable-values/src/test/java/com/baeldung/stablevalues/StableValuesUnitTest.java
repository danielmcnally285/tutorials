package com.baeldung.stablevalues;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;

public class StableValuesUnitTest {

    @Test
    void testStableList() {
        final List<Integer> fiveTimesTable = StableValue.list(11, index -> index * 5);
        assertThat(fiveTimesTable.get(0)).isEqualTo(0);
        assertThat(fiveTimesTable.get(1)).isEqualTo(5);
        assertThat(fiveTimesTable.get(10)).isEqualTo(50);
    }
}