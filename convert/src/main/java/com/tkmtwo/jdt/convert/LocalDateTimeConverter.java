package com.tkmtwo.jdt.convert;




import static com.google.common.base.TkmTwoStrings.blankToNull;

import com.google.common.base.CharMatcher;
import com.tkmtwo.jdt.format.IsoDateTimeFormatters;
import java.time.LocalDateTime;
import org.springframework.core.convert.converter.Converter;

public final class LocalDateTimeConverter
  implements Converter<String, LocalDateTime> {
  
  @Override
  public LocalDateTime convert(String s) {
    return IsoDateTimeFormatters.parseLocalDateTime(s);
  }
}
