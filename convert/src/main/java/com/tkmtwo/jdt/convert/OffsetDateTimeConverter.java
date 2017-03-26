package com.tkmtwo.jdt.convert;




import static com.google.common.base.TkmTwoStrings.blankToNull;

import com.google.common.base.CharMatcher;
import com.tkmtwo.jdt.format.IsoDateTimeFormatters;
import java.time.OffsetDateTime;
import org.springframework.core.convert.converter.Converter;

public final class OffsetDateTimeConverter
  implements Converter<String, OffsetDateTime> {
  
  @Override
  public OffsetDateTime convert(String s) {
    return IsoDateTimeFormatters.parseOffsetDateTime(s);
  }
}
