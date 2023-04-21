public enum EnumDay {

   LUNI("LU"), MARTI("MA"), MIERCURI("MI"),
   JOI("JO"), VINERI("VI"), SAMBATA("SA"), DUMINICA("DU");

   private String symbol;

   EnumDay(String symbol) {
      this.symbol = symbol;
   }

   public String getSymbol() {
      return symbol;
   }
}
