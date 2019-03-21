package com.test.quickstart;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   ParsingTests.class, ValidationTests.class
})

public class TestSuite {   
}
