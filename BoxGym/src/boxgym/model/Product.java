package boxgym.model;

public class Product {

    private int productId; //Identificador
    private byte[] photo; //Foto
    private String createdAt; //Criado em
    private String updatedAt; //Atualizado em

    public Product() {

    }

    //Construtor CREATE
    public Product(byte[] photo) {
        this.photo = photo;
    }

    //Construtor UPDATE
    public Product(int productId, byte[] photo) {
        this.productId = productId;
        this.photo = photo;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
