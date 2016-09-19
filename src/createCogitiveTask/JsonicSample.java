package createCogitiveTask;

//JSONICをインポート
import net.arnx.jsonic.JSON;

public class JsonicSample {
 public static void main(String[] args) {

     
     // 配列を含むJSON形式のデータを用意
     final String hoges = "{\"id\":\"10\",\"hoges\":[{\"name\":\"test\",\"age\":1},{\"name\":\"sample2\",\"age\":2}]}";

     final HogeList hogeList = JSON.decode(hoges, HogeList.class);

     // hogesのようにHogeの形式の配列を持つJSONを、HogeクラスのListをもつクラスにデコードすることが出来ます
     System.out.println("hogeList.id:" + hogeList.getId());
     for (final Hoge tmp : hogeList.getHoges()) {
         System.out.println("tmp.name:" + tmp.getName());
         System.out.println("tmp.age:" + tmp.getAge());
     }
 } 
}
