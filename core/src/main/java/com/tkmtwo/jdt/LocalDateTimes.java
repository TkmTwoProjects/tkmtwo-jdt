package com.tkmtwo.jdt;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.TkmTwoConditions.checkNotBlank;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Set;
import java.util.EnumSet;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

public class LocalDateTimes {
  
  public static Set<ChronoUnit> CHRONO_UNITS = EnumSet.allOf(ChronoUnit.class);
  
  public static ChronoUnit valueOfChronoUnit(String s) {
    String cuName = checkNotBlank(s, "Provided chrono unit name is blank.");
    return ChronoUnit.valueOf(cuName.toUpperCase());
  }
    
  public static ChronoUnit verifyChronoUnit(String s) {
    checkNotNull(s, "Need a string.");
    
    for (ChronoUnit cu : CHRONO_UNITS) {
      if (s.equalsIgnoreCase(cu.name())) {
        return cu;
      }
    }
    
    throw new IllegalArgumentException("ChronoUnit with name " + s + " not found.");
  }
  
  public static LocalDateTime now() {
    return floor(ChronoUnit.SECONDS);
  }
  
  public static LocalDateTime floor(ChronoUnit cu) {
    return floor(LocalDateTime.now(), cu);
  }
  public static LocalDateTime floor(LocalDateTime ldt, ChronoUnit cu) {
    checkNotNull(ldt, "LocalDateTime is null.");
    checkNotNull(cu, "ChronoUnit is null.");

    LocalDateTime rldt = null;
    
    //private static final LocalDateTime REFERENCE_LDT = LocalDateTime.of(1981, 8, 3, 4, 5, 5, 333);
    switch (cu) {
    case SECONDS:
      rldt = LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(), ldt.getMinute(), ldt.getSecond(), 0);
      break;
    case MINUTES:
      rldt = LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(), ldt.getMinute(), 0, 0);
      break;
    case HOURS:
      rldt = LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), ldt.getHour(), 0, 0, 0);
      break;
    case DAYS:
      rldt = LocalDateTime.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth(), 0, 0, 0, 0);
      break;
    case MONTHS:
      rldt = LocalDateTime.of(ldt.getYear(), ldt.getMonth(), 1, 0, 0, 0, 0);
      break;
    case YEARS:
      rldt = LocalDateTime.of(ldt.getYear(), 1, 1, 0, 0, 0, 0);
      break;
    default:
      throw new UnsupportedTemporalTypeException("ChronoUnit " + cu.name() + " not supported for ceiling/floor operations.");
    }
      
    return rldt;
  }

  public static LocalDateTime ceiling(ChronoUnit cu) {
    return ceiling(LocalDateTime.now(), cu);
  }
  public static LocalDateTime ceiling(LocalDateTime ldt, ChronoUnit cu) {
    LocalDateTime floor = floor(ldt, cu);
    return floor.plus(1L, cu);
  }
  
  
  public static LocalDateTime calculate(String s) {
    LocalDateTime ldt = LocalDateTime.now();
    String spec = checkNotBlank(s, "String spec is null.");
    
    Pattern basePattern = Pattern.compile("[a-zA-Z]+");
    Matcher baseMatcher = basePattern.matcher(spec);
    if (baseMatcher.lookingAt()) {
      ldt = floor(ldt, valueOfChronoUnit(baseMatcher.group(0)));
    }
    
    return calculate(ldt, spec);
  }
  
  public static LocalDateTime calculate(LocalDateTime ldt, String s) {
    LocalDateTime rldt = checkNotNull(ldt, "LocalDateTime is null.");
    String spec = checkNotBlank(s, "String spec is null.");
    
    Pattern p = Pattern.compile("([+\\-])(\\d+)([a-zA-Z]+)");
    Matcher m = p.matcher(spec);
    while (m.find()) {
      String op = m.group(1);
      String num = m.group(2);
      String unit = m.group(3);
      
      if ("+".equals(op)) {
        rldt = rldt.plus(Long.parseLong(num), valueOfChronoUnit(unit));
      } else if ("-".equals(op)) {
        rldt = rldt.minus(Long.parseLong(num), valueOfChronoUnit(unit));
      }
      
    }
    
    return rldt;
  }
  
  
}
