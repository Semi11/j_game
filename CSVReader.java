import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;

class CSVReader {

  public List<HashMap<String, String>> read(String file_name) {
    List<HashMap<String, String>> ret = new ArrayList<HashMap<String, String>>();    
    File csv = new File(file_name);
    BufferedReader br = null;

    try {
      br = new BufferedReader(new FileReader(csv));

      String line = "";
      List<String> hedder_words = new ArrayList<String>();
      boolean is_set_hedder = false;

      while ((line = br.readLine()) != null) {
        HashMap<String, String> tmp_map= new HashMap<String, String>();

        int idx_from = 0;
        int idx_to = 0;
        int word_idx = 0;

        while (idx_from < line.length()) {
          idx_to = line.indexOf(",", idx_from);

          if (idx_to == -1) {
            idx_to =line.length();
          }

          String word = line.substring(idx_from, idx_to);

          if (is_set_hedder) {
            tmp_map.put(hedder_words.get(word_idx), word);
          } else {
            hedder_words.add(word);
            
          }
          word_idx++;
          idx_from = idx_to+1;
        }

	if (is_set_hedder) {
	    ret.add(tmp_map);
	}else{
	    is_set_hedder = true;
	}

      }
    }
    catch(FileNotFoundException e) {
      e.printStackTrace();
    }
    catch(IOException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (br != null) {
          br.close();
        }
      }
      catch(IOException e) {
        e.printStackTrace();
      }
    }

    return ret;
  }
}
