package nlp;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.annolab.tt4j.TokenHandler;
import org.annolab.tt4j.TreeTaggerWrapper;

public class MorphologicalAnalysis {

	public static boolean v=false;

	public static boolean checkV(String str) throws Exception{
		// Point TT4J to the TreeTagger installation directory. The executable is expected
        // in the "bin" subdirectory - in this example at "/opt/treetagger/bin/tree-tagger"
        //System.setProperty("treetagger.home", "/opt/treetagger");
        System.setProperty("treetagger.home", "/treetagger");// ここに本体が必要　\treetagger\bin\tree-tagger.exe　これのことのようです。

        v = false;


        TreeTaggerWrapper<String> tt = new TreeTaggerWrapper<String>();
        List<String> sampleStr  = tokenize(str);


                //tt.setModel("/opt/treetagger/models/english.par:iso8859-1");
                tt.setModel("/treetagger/lib/english-utf8.par:iso8859-1");// ここにpar パラメータファイルなるものが必要


                tt.setHandler(new TokenHandler<String>() {

                		public String[] str = {"VB","VBD","VBZ","VBP","VD","VDD","VDZ","VDP","VH","VHD","VHZ","VHP","VV","VVD"};
                		List<String> list = Arrays.asList(str);
                		
                        public void token(String token, String pos, String lemma) {

                                if(list.contains(pos)){
                                    System.out.println(token + "\t" + pos + "\t" + lemma);
                                v = true;
                                }

                        }


                });
                //tt.process(asList(new String[] { "This", "is", "a", "test", ".", "I","bought","the","car",".","I've","bought","a","car","."}));
                tt.process(sampleStr);

                return v;
	}

	  public static
      List<String> tokenize(
                      final String aString)
      {
              List<String> tokens = new ArrayList<String>();
              BreakIterator bi = BreakIterator.getWordInstance();
              bi.setText(aString);
              int begin = bi.first();
              int end;
              for (end = bi.next(); end != BreakIterator.DONE; end = bi.next()) {
                      String t = aString.substring(begin, end);
                      if (t.trim().length() > 0) {
                              tokens.add(aString.substring(begin, end));
                      }
                      begin = end;
              }
              if (end != -1) {
                      tokens.add(aString.substring(end));
              }
              return tokens;
      }

}
