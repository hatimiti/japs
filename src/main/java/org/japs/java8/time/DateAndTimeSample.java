package org.japs.java8.time;

import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DateAndTimeSample {

	public static void main(String[] args) {
		nowDateTimes();
		ZonedDateTime dt = createDate();
		ofLocalizedDate(dt);
		format();
		calcLocalDate();
		calcLocalDateTime();
		
		log("-- ZonedDateTime --");
		log(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM)));
	
		zoneOffset();
		summerTime();
		
		log("-- Period --");
		
		LocalDate ld0 = LocalDate.parse("2016年01月31日", DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
		LocalDate ld1 = LocalDate.parse("2016年02月29日", DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
		LocalDate ld2 = LocalDate.parse("2016年03月29日", DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
		LocalDate ld3 = LocalDate.parse("2016年03月30日", DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
		log(Period.between(ld0, ld1));
		log(Period.between(ld1, ld0));
		log(Period.between(ld1, ld2));
		log(Period.between(ld1, ld3));
		
		log(ld0.plusMonths(1));
		log(ld0.plus(Period.between(ld0, ld1)));
		log(ld0.plus(Period.between(ld1, ld2)));
		
		log(Period.ofDays(365));
		
	}
	
	private static void zoneOffset() {
		log("-- ZoneOffset --");
		log(ZoneOffset.MAX);
		log(ZoneOffset.MIN);
		log(ZoneOffset.UTC);
		log(ZoneOffset.of("Z").equals(ZoneOffset.UTC));
		log(ZoneOffset.of("+09:00").equals(ZoneOffset.of("+09:00")));
	}

	private static void summerTime() {
		log("-- summer time --");
		LocalDateTime ld = LocalDateTime.of(2016, Month.MARCH, 13, 1, 0, 0);
		ZonedDateTime lzd = ZonedDateTime.of(ld, ZoneId.of("America/Los_Angeles"));
		log(lzd);
		log(lzd.plusMinutes(59));
		log(lzd.plusHours(1));
		log(lzd.plusHours(2));
		log(lzd.plusHours(3));
	}

	private static void calcLocalDateTime() {
		log("-- calcLocalDateTime --");
		LocalDateTime dt1 = LocalDateTime.of(2016, Month.MAY, 23, 1, 0, 20);
		log(dt1.plusMinutes(60));
		log(dt1.plusNanos(1000 * 1000 * 1000));
		
		LocalDateTime dt2 = LocalDateTime.of(2016, Month.MAY, 23, 10, 0, 0);
		log(dt2.minusHours(17));
	}

	private static void calcLocalDate() {
		log("-- calcLocalDate --");
		LocalDate d1 = LocalDate.parse("1996年05月23日", ofPattern("yyyy年MM月dd日"));
		log("%sの8日後は%s", d1, d1.plusDays(8));
		LocalDate d2 = LocalDate.parse("1996年05月31日", ofPattern("yyyy年MM月dd日"));
		log("%sの1ヶ月後は%s", d2, d2.plusMonths(1));
		LocalDate d3 = LocalDate.parse("1996年02月29日", ofPattern("yyyy年MM月dd日"));
		log("%sの1ヶ月前は%s", d3, d3.minusMonths(1));
		log("%sの1年前は%s", d3, d3.minusYears(1));
		log("%sの1年後は%s", d3, d3.plusYears(1));
		log("%sの4年後は%s", d3, d3.plusYears(4));
		log("%sの3年後の1年後は%s", d3, d3.plusYears(1).plusYears(4));
		LocalDate d4 = LocalDate.parse("1996年01月31日", ofPattern("yyyy年MM月dd日"));
		log("%sの1ヶ月後は%s", d4, d4.plusMonths(1));
	}

	private static void format() {
		log("-- format --");
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss");
		log(now.format(fmt1));
		DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("MMMM");
		log(now.format(fmt2));
		DateTimeFormatter fmt3 = DateTimeFormatter.ofPattern("MMMM", Locale.US);
		log(now.format(fmt3));
	}

	private static ZonedDateTime createDate() {
		log("-- createDate --");
		log(LocalDate.of(1995, 5, 23));
		ZonedDateTime dt = ZonedDateTime.parse("2017-05-23T23:12:00.123+08:00[Asia/Tokyo]");
		log(dt);
		log(dt.getZone());
		return dt;
	}

	private static void ofLocalizedDate(ZonedDateTime dt) {
		log("-- ofLocalizedDate --");
		log("ofLocalizedDate.FULL  : %s", DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(dt));
		log("ofLocalizedDate.LONG  : %s", DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(dt));
		log("ofLocalizedDate.MEDIUM: %s", DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(dt));
		log("ofLocalizedDate.SHORT : %s", DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(dt));
		
		log(dt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		
	}

	private static void nowDateTimes() {
		log("-- nowDateTimes -- ");
		log("LocalDateTime : %s", LocalDateTime.now());
		log("OffsetDateTime: %s", OffsetDateTime.now());
		log("ZonedDateTime : %s", ZonedDateTime.now());
		log("LocalTime     :            %s", LocalTime.now());
		log("OffsetTime    :            %s", OffsetTime.now());
	}
	
	public static void log(Object obj) {
		System.out.println(obj);
	}

	public static void log(String format, Object... obj) {
		System.out.format(format, obj).println();
	}
	
}
