package com.test.quickstart;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   ParsingTests.class, ValidationTests.class, TypeConverterTests.class, TypeResolverTests.class, VersionValidatorTests.class, DependencyTests.class, DuplicateKeysTest.class, GHRunner.class
})

public class TestSuite {   
}
