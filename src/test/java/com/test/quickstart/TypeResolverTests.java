package com.test.quickstart;


import static org.junit.Assert.assertTrue;


import org.junit.Test;

public class TypeResolverTests {
	TypeResolver t = new TypeResolver();
	@Test
	public void checkStringTest()
	{
		String testString = "foo";
		assertTrue(t.checkString(testString));
	}
	@Test
	public void checkStringListTest()
	{
		String testString = "[foo,bar]";
		assertTrue(t.checkStringList(testString));
	}
	@Test
	public void checkMapTest()
	{
		String testString = "{foo=bar}";
		assertTrue(t.checkMap(testString));
	}
	@Test
	public void checkNestedMapTest()
	{
		String testString = "{foo= {bar=baz}}";
		assertTrue(t.checkNestedMap(testString));
	}
	@Test
	public void checkMapListTest()
	{
		String testString = "[{foo=bar},{baz=qux}]";
		assertTrue(t.checkMapList(testString));
	}
}
