package org.jxls3;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.jxls.Jxls3Tester;
import org.jxls.TestWorkbook;
import org.jxls.entity.Employee;
import org.jxls.transform.poi.JxlsPoiTemplateFillerBuilder;

public class Issue321Test {

    @Test
    public void testMoreCommandsOnSameLine() throws IOException {
        Map<String, Object> data = createData();
        Jxls3Tester tester = Jxls3Tester.xlsx(getClass());
        tester.test(data, JxlsPoiTemplateFillerBuilder.newInstance());
        try (TestWorkbook w = tester.getWorkbook()) {
            int afterData = data.size() + 1;
            assertEquals("SOME", w.getCellValueAsString(afterData, 0));
            assertEquals("SPACE", w.getCellValueAsString(afterData, 1));
            assertEquals("User", w.getCellValueAsString(afterData + 1, 0));
            assertEquals("Name", w.getCellValueAsString(afterData + 1, 1));
            assertEquals("LAST ROW", w.getCellValueAsString(afterData + 2, 0));
            assertEquals("AT LAST", w.getCellValueAsString(afterData + 3, 1));
        }
    }

    private Map<String, Object> createData() {
        Map<String, Object> data = new HashMap<>();
        data.put("employees", Employee.generateSampleEmployeeData());
        return data;
    }
}
