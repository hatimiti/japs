package org.japs.basic.fileio.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public final class Main {

	public static void main(String[] args) throws IOException {

		for (;;) {

			final Operation op = inputOperation();
			
			if (op == Operation.NONE) {
				System.out.println("終了します．");
				System.exit(0);
			}
			
			// final OperationTarget opt = inputOperationTarget();
			
			System.out.println("操作対象を入力してください(e.g: /japs/dir, /japs/sample.txt)");
			System.out.print("-> ");
			final FileSystemOperator operator = DirectoryOperator.of(readFromSystemIn());

			switch (op) {
			case CREATE:
				operator.create(); break;
			case MOVE:
				System.out.println("移動先を入力してください．");
				System.out.print("-> ");
				operator.move(Paths.get(readFromSystemIn())); break;
			case COPY:
				System.out.println("コピー先を入力してください．");
				System.out.print("-> ");
				operator.copy(Paths.get(readFromSystemIn())); break;
			case DELETE:
				operator.delete(); break;
			case SHOW:
				operator.show(); break;
			default: break;
			}

		}

		// Files.newDirectoryStream(path1)
		// .forEach(_it -> System.out.println(_it.getFileName()));
		//
		// System.out.println("--------------");
		// FileSystemProvider.installedProviders().stream()
		// .map(_it -> _it.getScheme())
		// .forEach(System.out::println);

	}

	public static String readFromSystemIn() throws IOException {
		return new BufferedReader(new InputStreamReader(System.in)).readLine();
	}

	private static Operation inputOperation() throws IOException {
		Operation op;
		for (;;) {
			System.out.println(String.format("ファイル操作を行います．(%s)", Operation.showHelp()));
			System.out.print("操作を入力してください ->");
			try {
				op = Operation.of(readFromSystemIn());
				break;
			} catch (IllegalArgumentException e) {
				System.out.println("入力した操作が不正です．再度入力してください．");
			}
		}
		return op;
	}

	private static OperationTarget inputOperationTarget() throws IOException {
		OperationTarget target;
		for (;;) {
			System.out.println(String.format("操作対象を入力してください．(%s)", OperationTarget.showHelp()));
			System.out.print("->");
			try {
				target = OperationTarget.of(readFromSystemIn());
				break;
			} catch (IllegalArgumentException e) {
				System.out.println("入力した操作対象が不正です．再度入力してください．");
			}
		}
		return target;
	}

}

class DirectoryOperator implements FileSystemOperator {
	
	private Path target;
	
	private DirectoryOperator(String path) {
		this.target = Paths.get(path);
	}
	
	public static final DirectoryOperator of(String path) {
		return new DirectoryOperator(path);
	}

	@Override
	public void create() {
		try {
			if (Files.exists(target)) {
				System.out.println(target + " は既に存在します．");
				return;
			}
			Files.createDirectory(target);
			System.out.println("ディレクトリを作成しました．" + target);
		} catch (IOException e) {
			System.out.println("ディレクトリの作成に失敗しました．");
		}
	}

	@Override
	public void move(Path to) {
		
		if (!isValidDir(target)) {
			System.out.println("操作対象が不正です．");
			return;
		}

		try {
			Files.move(target, to, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("ディレクトリの移動に失敗しました．" + e);
		}
	}

	@Override
	public void copy(Path to) {
		
		if (!isValidDir(target)) {
			System.out.println("操作対象が不正です．");
			return;
		}

		try {
			Files.copy(target, to);
		} catch (IOException e) {
			System.out.println("ディレクトリのコピーに失敗しました．");
		}
	}

	@Override
	public void delete() {
		
		if (!isValidDir(target)) {
			System.out.println("操作対象が不正です．");
			return;
		}

		try {
			Files.deleteIfExists(target);
			System.out.println("ディレクトリを削除しました．" + target);

		} catch (IOException e) {
			System.out.println("ディレクトリの削除に失敗しました．");
		}
	}

	@Override
	public void show() {
		
		if (!isValidDir(target)) {
			System.out.println("操作対象が不正です．");
			return;
		}
		
		try {
			final StringBuilder sb = new StringBuilder();
			final AtomicInteger counter = new AtomicInteger(0);
			Files.newDirectoryStream(target).forEach(new Consumer<Path>() {
				@Override
				public void accept(Path t) {
					sb.append(t.getFileName()).append(" | ");
					if (counter.incrementAndGet() % 3 == 0) {
						sb.append(System.lineSeparator());
					}
				}
			});
			System.out.println(sb);
		} catch (IOException e) {
			System.out.println(String.format("表示処理に失敗しました．理由=%s", e.getMessage()));
		}
	}

	@Override
	public Path getTarget() {
		return this.target;
	}
	
	private boolean isValidDir(Path p) {
		return p != null && Files.isDirectory(p);
	}
	
}

interface FileSystemOperator {
	
	void create();
	void move(Path to);
	void copy(Path to);
	void delete();
	void show();
	Path getTarget();
	
}

enum Operation {
	NONE, CREATE, MOVE, COPY, DELETE, SHOW,;

	public static final Operation of(String type) {
		switch (type) {
		case "0":
			return NONE;
		case "1":
			return CREATE;
		case "2":
			return MOVE;
		case "3":
			return COPY;
		case "4":
			return DELETE;
		case "5":
			return SHOW;
		default:
			throw new IllegalArgumentException("Invalid argument.");
		}
	}

	public static String showHelp() {
		return "EXIT: 0, CREATE: 1, MOVE: 2, COPY: 3, DELELE: 4, SHOW: 5";
	}
}

enum OperationTarget {
	DIRECTORY, FILE,;

	public static final OperationTarget of(String type) {
		switch (type) {
		case "1":
			return DIRECTORY;
		case "2":
			return FILE;
		default:
			throw new IllegalArgumentException("Invalid argument.");
		}
	}

	public static String showHelp() {
		return "DIRECTORY: 1, FILE: 2";
	}
}