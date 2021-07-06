/* Decompiler 3ms, total 173ms, lines 48 */
package modelTables;

public class modelTableMedicamento {
   String id;
   String nome;
   String quantidade;
   String cas;

   public modelTableMedicamento(String id, String nome, String quantidade, String cas) {
      this.id = id;
      this.nome = nome;
      this.quantidade = quantidade;
      this.cas = cas;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getNome() {
      return this.nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getQuantidade() {
      return this.quantidade;
   }

   public void setQuantidade(String quantidade) {
      this.quantidade = quantidade;
   }

   public String getCas() {
      return this.cas;
   }

   public void setCas(String cas) {
      this.cas = cas;
   }
}
