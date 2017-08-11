package com.newshunt.analyticsplatform.flink.nhevents;

import java.util.regex.*;

/**
 * Common fields for Apache Log demo.
 */
interface LogExample {
  /** The number of fields that must be found. */
  public static final int NUM_FIELDS = 9;

  /** The sample log entry to be parsed. */
  public static final String logEntryLine = "37.224.226.189 241658 - [03/Aug/2016:11:59:59 +0530] \"GET /cr.action?act=showList&fKey=khaskhab&npKey=khaskhab&ctKey=dhramadyatmikta&client=android&res=480x854&clientId=828984706&appVer=4.62.59&osVersion=5.1&brand=NewsHunt&long=&lat=&cellid=8539183-420-01-4281----Gsm&isReg=N&imgFmt=7&featureMask=7340031&langMask=4095&selLang=2&udid=911441950207930&conn=4G&groupKey=news&readerMask=1&acqFKey=hunt HTTP/1.1\" 200 4386 \"-\" \"Mozilla/5.0 (Linux; Android 5.1; Micromax Q370 Build/LMY47D; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.121 Mobile Safari/537.36\" \"-\" \"-\""
;
}


/**
 * Parse an Apache log file with Regular Expressions
 */
public class LogRegExp implements LogExample {

  public static void main(String argv[]) {

    String logEntryPattern = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";

    System.out.println("Using RE Pattern:");
    System.out.println(logEntryPattern);

    System.out.println("Input line is:");
    System.out.println(logEntryLine);

    Pattern p = Pattern.compile(logEntryPattern);
    Matcher matcher = p.matcher(logEntryLine);
    if (!matcher.matches() || 
      NUM_FIELDS != matcher.groupCount()) {
      System.err.println("Bad log entry (or problem with RE?):");
      System.err.println(logEntryLine);
      return;
    }
    System.out.println("IP Address: " + matcher.group(1));
    System.out.println("Date&Time: " + matcher.group(4));
    System.out.println("Request: " + matcher.group(5));
    System.out.println("Response: " + matcher.group(6));
    System.out.println("Bytes Sent: " + matcher.group(7));
    if (!matcher.group(8).equals("-"))
      System.out.println("Referer: " + matcher.group(8));
    System.out.println("Browser: " + matcher.group(9));
  }
}

