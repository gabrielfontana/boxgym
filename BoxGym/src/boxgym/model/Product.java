package boxgym.model;

public class Product {

    private int productId; //Identificador
    private byte[] image; //Imagem
    private String createdAt; //Criado em
    private String updatedAt; //Atualizado em

    public Product() {

    }

    //Construtor CREATE
    public Product(byte[] image) {
        this.image = image;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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
