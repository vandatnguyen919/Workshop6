// =========================================================
// Do NOT modify this file 
// =========================================================
//Interface for a group of objects
public interface I_List {
    void AddFromFile(String fName);
    void saveToFile(String fName);
    // tim ma cua 1 phan tu trong nhom
    int find(String code);
    // them 1 phan tu, co nhap lieu
    void add(); 
    // xoa 1 phan tu, co nhap lieu
    void remove();
    // update 1 phan tu co nhap lieu
    void update();
    // sap xep ds
    void sort();
    // xuat ds
    void print();
}
// =========================================================
// Do NOT modify this file 
// =========================================================