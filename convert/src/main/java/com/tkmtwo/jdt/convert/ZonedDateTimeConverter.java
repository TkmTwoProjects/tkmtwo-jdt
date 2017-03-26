package com.tkmtwo.jdt.convert;




import static com.google.common.base.TkmTwoStrings.blankToNull;

import com.google.common.base.CharMatcher;
import com.tkmtwo.jdt.format.IsoDateTimeFormatters;
import java.time.ZonedDateTime;
import org.springframework.core.convert.converter.Converter;

public final class ZonedDateTimeConverter
  implements Converter<String, ZonedDateTime> {
  
  @Override
  public ZonedDateTime convert(String s) {
    return IsoDateTimeFormatters.parseZonedDateTime(s);
  }
}
