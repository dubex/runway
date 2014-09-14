package org.cinchapi.runway;

import org.cinchapi.concourse.test.ClientServerTest;
import org.cinchapi.runway.Record;
import org.cinchapi.runway.Unique;
import org.junit.Assert;
import org.junit.Test;

public class RecordTest extends ClientServerTest {

    @Override
    protected String getServerVersion() {
        return "0.4.1";
    }

    @Override
    public void beforeEachTest() {
        Record.setConnectionInformation("localhost",
                this.server.getClientPort(), "admin", "admin");
    }
    
    @Test
    public void testCannotAddDuplicateValuesForUniqueVariable(){
        Mock person = Record.create(Mock.class);
        person.name = "Jeff Nelson";
        Assert.assertTrue(person.save());
        
        Mock person2 = Record.create(Mock.class);
        person2.name = "Jeff Nelson";
        Assert.assertFalse(person2.save());
        
        person2.name = "Jeffery Nelson";
        Assert.assertTrue(person2.save());      
    }

    class Mock extends Record {

        @Unique
        public String name;
        
        public Integer age;

    }

}