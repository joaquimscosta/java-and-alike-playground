import lombok.Getter;
import lombok.Setter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;

public class Main{

  private static final GsonBuilder GSONBUILDER = new GsonBuilder();

  public static void main(String[] args) {
    Gson gson = GSONBUILDER.create();
    String json = gson.toJson(new CreditCard("Joaquim Costa", "1234-4567-6788-4434", 1000.00));
    System.out.println(json);
  }
  
  @Getter
  @Setter
  @AllArgsConstructor
  public class CreditCard{
    private String name;
    private String cardNumber;
    private double creditLimit;
  }
}