package org.japs.basic.type._class;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * メールアドレスの情報を表現するクラス．
 * @author m-kakimi
 *
 * <c> ::= any one of the 128 ASCII characters, but not any
 *    <special> or <SP>
 */
public class MailAddress {
	
	public static final char SEPARATOR = '@';
	
	/**
     * <special> ::= "<" | ">" | "(" | ")" | "[" | "]" | "\" | "."
     *     | "," | ";" | ":" | "@"  """ | the control
     *     characters (ASCII codes 0 through 31 inclusive and *     127)
     */
	private final static List<Character> SPECIAL_CHARS
		= unmodifiableList(asList('<', '>', '(', ')', '[', ']', '\\', '.', ',', ';', ':', '@', '\"'));
	
	/**
     * <SP> ::= the space character (ASCII code 32)
	 */
	private final static char SPACE_CHAR = ' ';
	
	/** ローカル部分 */
	private String localPart;
	/** ドメイン部分 */
	private String domain;
	
	public MailAddress(String localPart, String domain) {
		this.localPart = localPart;
		this.domain = domain;
	}
	
	public String getLocalPart() {
		return localPart;
	}
	
	public String getDomain() {
		return domain;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MailAddress)) {
			return false;
		}
		MailAddress another = (MailAddress) obj;
		return Objects.equals(localPart, another.localPart)
			&& Objects.equals(domain, another.domain);
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public String toString() {
		return this.localPart + SEPARATOR + this.domain;
	}
	
	/**
	 * <special> ::= "<" | ">" | "(" | ")" | "[" | "]" | "\" | "."
	 * | "," | ";" | ":" | "@"  """ | the control
	 * characters (ASCII codes 0 through 31 inclusive and 127)
	 * @return
	 */
	public boolean isValidLocalPart() {
		for (int i = 0; i < localPart.length(); i++) {
			char c = localPart.charAt(i);
			if (c < 31 || 127 < c) {
				return false;
			}
			if (SPECIAL_CHARS.contains(c)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * <let-dig-hyp> ::= <a> | <d> | "-"
	 * <a> ::= any one of the 52 alphabetic characters A through Z
	 *  in upper case and a through z in lower case
	 * <d> ::= any one of the ten digits 0 through 9
	 * @return
	 */
	public boolean isValidDomain() {
		for (int i = 0; i < domain.length(); i++) {
			char c = domain.charAt(i);
			if (!(('A' <= c && c <= 'Z')
					|| ('a' <= c && c <= 'z')
					|| ('0' <= c && c <= '9')
					|| c == '.' || c == '-')) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {

		assert new MailAddress("hatti33", "gmail.com").toString()
			.equals("hatti33@gmail.com");
		
		assert new MailAddress("hatti33", "gmail.com")
			.equals(new MailAddress("hatti33", "gmail.com"));

		assert !new MailAddress("hatti33", "gmail.com")
			.equals(new MailAddress("hatti333", "gmail.com"));

		assert new MailAddress("a", "b").isValidLocalPart();
		assert new MailAddress("-", "b").isValidLocalPart();
		assert !new MailAddress("@", "b").isValidLocalPart();

		assert new MailAddress("a", "b").isValidDomain();
		assert new MailAddress("a", "-").isValidDomain();
		assert !new MailAddress("a", "@").isValidDomain();

		assert new MailAddress("a", "b").hashCode()
			== new MailAddress("a", "b").hashCode();
		assert new MailAddress("a", "b").hashCode()
			!= new MailAddress("b", "a").hashCode();
		
		Map<MailAddress, String> map = new HashMap<>();
		map.put(new MailAddress("x", "a"), "mkakimi");
		map.put(new MailAddress("y", "b"), "xxxxxxx");
		assert map.get(new MailAddress("x", "a")).equals("mkakimi");
	}
}