package org.japs.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DejizoSearcher {

	public static final String ITEM_LIST_URI = "http://public.dejizo.jp/NetDicV09.asmx/SearchDicItemLite";
	public static final String CONTENT_URI = "http://public.dejizo.jp/NetDicV09.asmx/GetDicItemLite";

	public static void main(String... args) {
		HttpSearcher items = new HttpSearcher(ITEM_LIST_URI)
			.param("Dic", "EdictJE")
			.param("Word", "禁止")
			.param("Scope", "HEADWORD")
			.param("Match", "CONTAIN")
			.param("Merge", "OR")
			.param("Prof", "")
			.param("PageSize", "5")
			.param("PageIndex", "0")
		;
		
		System.out.println(items.execute());
	}
}

class HttpSearcher {

	private final String uri;
	private final Map<String, String> params = new HashMap<>();

	public HttpSearcher(String uri) {
		this.uri = uri; 
	}

	public HttpSearcher param(String name, String value) {
		Objects.requireNonNull(name);
		this.params.put(name, value);
		return this;
	}

	public String execute() {
		int statusCode;
		try {
			URL url = new URL(uri + buildParam());
			System.out.println(url);
			HttpURLConnection con = null;
			try {
				con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				statusCode = con.getResponseCode();
				if (statusCode == HttpURLConnection.HTTP_OK) {
					try (InputStreamReader isr = new InputStreamReader(con.getInputStream(),
							StandardCharsets.UTF_8);
						BufferedReader reader = new BufferedReader(isr)) {
						return reader.lines().collect(Collectors.joining(System.lineSeparator()));
					}
				}
			} catch (Exception e) {
				throw e;
			} finally {
				if (con != null) {
					con.disconnect();
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return "statusCode = " + statusCode;
	}

	private String buildParam() {
		StringBuilder ret = new StringBuilder("?");
		params.entrySet().stream()
		.forEach(es -> {
			ret.append(String.format("%s=%s&", es.getKey(), es.getValue()));
		});
		return ret.toString();
	}
}
