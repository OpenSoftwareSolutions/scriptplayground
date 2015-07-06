package test.org.oss.cfg;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.oss.cfg.exception.AnalyzerException;
import org.oss.cfg.lexer.Lexer;
import org.oss.cfg.parser.Parser;
import org.oss.cfg.parser.Rule;
import org.oss.cfg.token.Token;
import org.oss.cfg.util.FileUtil;


public class CFGTest {

	@Test
	public void test() throws IOException, AnalyzerException {


		String sourceCode = FileUtil.readCodeToString("resources/script/Test.java");

		Lexer lexer = new Lexer();
		lexer.tokenize(sourceCode);

		Parser parser = new Parser();
		List<Token> filteredTokens = lexer.getFilteredTokens();

		System.out.println("Filtered Token");
		for (Token token : filteredTokens) {
			System.out.println(token.toString());
		}

		File grammarFile = FileUtil.readGrammar("resources/grammar.txt");
		parser.parse(grammarFile, filteredTokens);

		int i = 0;
		for (Token token : lexer.getTokens()) {
			if (token.getTokenType().isAuxiliary())
				System.out.println("   " + token.toString() + "\n");
			else {
				i++;
				System.out.println(i + "   " + token.toString() + "\n");
			}
		}

		for (Rule r : parser.getSequenceOfAppliedRules()) {
			System.out.println(r.toString() + "\n");
		}

	}

}