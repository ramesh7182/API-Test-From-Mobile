package com.ramesh7182;

import com.opencsv.CSVReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class TestDataProvider
{
    public static Log LOG = LogFactory.getLog(TestDataProvider.class);
    public static String testCaseName;
    private static String testdatapath = ".\\src\\main\\resources\\testdata.csv";


    public static Object[][] readTestData(Method method)
    {
        testCaseName = method.getName();
        LOG.info("Current Directory "+System.getProperty("user.dir"));
        LOG.info("Test Case Being Executed is : "+testCaseName);
        ArrayList<HashMap<Object,Object>> data = new ArrayList<>();
        Object[][] objects ;
        try {
            FileReader filereader = new FileReader(testdatapath);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            String[] key ;
            HashMap<Object, Object> row = new HashMap<>();
            key = csvReader.readNext();
            while ((nextRecord = csvReader.readNext()) != null) {
                row = new HashMap<>();
                int colCount = 0;
                if(nextRecord[0].equalsIgnoreCase(testCaseName) && nextRecord[1].equalsIgnoreCase("y")) {
                    for (String cell : nextRecord) {
                        row.put(key[colCount], cell);
                        colCount++;

                    }
                    data.add(row);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return getObjectFromHashMap(data);
    }

    public static Object[][] getObjectFromHashMap(ArrayList<HashMap<Object,Object>> map)
    {
        Object[][] obj = new Object[map.size()][1];
        for(int i=0;i<map.size();i++)
        {
            try {
                obj[i][0]=map.get(i);
            }catch (Exception e){
                LOG.info("Inside Exception");
                e.printStackTrace();}

        }
        return obj;

    }

}

