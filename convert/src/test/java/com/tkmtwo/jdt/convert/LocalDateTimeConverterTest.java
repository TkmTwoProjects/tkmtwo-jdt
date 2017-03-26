package com.tkmtwo.jdt.convert;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import com.tkmtwo.jdt.format.IsoDateTimeFormatters;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalDateTimeConverterTest {
  
  private static final LocalDateTime REFERENCE_LDT = LocalDateTime.of(1981, 8, 3, 4, 5, 5, 0);

  
  @Test
  public void testThis() {
    LocalDateTimeConverter converter = new LocalDateTimeConverter();
    
    assertEquals(REFERENCE_LDT, converter.convert("1981-08-03T04:05:05Z"));
    assertEquals(REFERENCE_LDT, converter.convert("1981-08-03T04:05:05+00:00"));
    assertEquals(REFERENCE_LDT, converter.convert("1981-08-03T04:05:05-00:00"));
    assertEquals(REFERENCE_LDT, converter.convert("1981-08-03T04:05:05"));
    assertEquals(REFERENCE_LDT, converter.convert("19810803T040505Z"));
    assertEquals(REFERENCE_LDT, converter.convert("19810803T040505+00:00"));
    assertEquals(REFERENCE_LDT, converter.convert("19810803T040505-00:00"));
    assertEquals(REFERENCE_LDT, converter.convert("19810803T040505"));
    
    assertEquals(REFERENCE_LDT, converter.convert("1981-08-03 04:05:05Z"));
    assertEquals(REFERENCE_LDT, converter.convert("1981-08-03 04:05:05+00:00"));
    assertEquals(REFERENCE_LDT, converter.convert("1981-08-03 04:05:05-00:00"));
    assertEquals(REFERENCE_LDT, converter.convert("1981-08-03 04:05:05"));
    assertEquals(REFERENCE_LDT, converter.convert("19810803 040505Z"));
    assertEquals(REFERENCE_LDT, converter.convert("19810803 040505+00:00"));
    assertEquals(REFERENCE_LDT, converter.convert("19810803 040505-00:00"));
    assertEquals(REFERENCE_LDT, converter.convert("19810803 040505"));

    
  }
  
  
}
