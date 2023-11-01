package ra.View.account.user;

import ra.config.Validate;
import ra.model.Category;
import ra.model.Product;
import ra.service.category.CategoryServiceIMPL;
import ra.service.category.ICategoryService;
import ra.service.product.IProductService;
import ra.service.product.ProductServiceIMPL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static ra.config.Color.RESET;

public class UserHomePage {
    ICategoryService categoryService = new CategoryServiceIMPL();
    IProductService productService = new ProductServiceIMPL();

    public void menu() {
        do {
            System.out.println("\033[1;94m╔══════════════════════════ TRANG CHỦ  ═══════════════════════════╗");
            System.out.println("\033[1;94m║                                                                 ║");
            System.out.println("\033[1;94m║              \033[1;97m1. Tìm kiếm sản phẩm"+RESET+"\033[1;94m                               ║");
            System.out.println("\033[1;94m║              \033[1;97m2. Hiển thị sản phẩm nổi bật"+RESET+"\033[1;94m                       ║");
            System.out.println("\033[1;94m║              \033[1;97m3. Hiển thị từng nhóm sản phẩm"+RESET+"\033[1;94m                     ║");
            System.out.println("\033[1;94m║              \033[1;97m4. Danh sách sản phẩm"+RESET+"\033[1;94m                              ║");
            System.out.println("\033[1;94m║              \033[1;97m5. Danh sách sắp xếp theo giá tằng dần"+RESET+"\033[1;94m             ║");
            System.out.println("\033[1;94m║              \033[1;97m6. Thêm vào giỏ hàng"+RESET+"\033[1;94m                               ║");
            System.out.println("\033[1;94m║              \033[1;97m0. Quay lại"+RESET+"\033[1;94m                                        ║");
            System.out.println("\033[1;94m║                                                                 ║");
            System.out.println("\033[1;94m╚═════════════════════════════════════════════════════════════════╝"+RESET);
            System.out.print("Mời lựa chọn (1/2/3/4/5/6/0): ");
            switch (Validate.validateInt()) {
                case 1:
                    handleFindProduct();
                    break;
                case 2:
                    handleDisplayTopTenProductByQuantityInStock();
                    break;
                case 3:
                    handleDisplayProductByCategoryId();
                    break;
                case 4:
                    handleDisplayProduct();
                    break;
                case 5:
                    handleDisplayProductByUnitPriceDecent();
                    break;
                case 6:
                    handleAddtoCart();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (true);
    }

    private void handleAddtoCart() {
        handleDisplayProduct();
        System.out.println("Mời thêm sản phẩm vào giỏ hàng của bạn: ");

    }

    private void handleDisplayProductByUnitPriceDecent() {
        System.out.println("Danh sách trước khi sắp xếp: ");
        handleDisplayProduct();
        System.out.println("Danh sách sau khi sắp xếp theo thứ tự giá giảm dần: ");
//        for (int i = 0; i < productService.findAll().size() - 1; i++) {
//            for (int j = 0; j < productService.findAll().size() - 1; j++) {
//                if (productService.findAll().get(j).getUnitPrice() < productService.findAll().get(j + 1).getUnitPrice()) {
//                    Product temp = productService.findAll().get(j);
//                    productService.findAll().set(j,productService.findAll().get(j+1));
//                    productService.findAll().set(j+1,temp);
//                }
//            }
//        }
        Collections.sort(productService.findAll());
        handleDisplayProduct();
        System.out.println("Đã sắp xếp xong!");
    }

    private void handleDisplayProduct() {
        for (Product pro : productService.findAll()) {
            if (pro.isStatus()) {
                System.out.println(pro);
            }
        }
    }

    private void handleDisplayProductByCategoryId() {
        handleDisplayProduct();
        System.out.println("Nhập vào mã danh mục bạn muốn tìm kiếm: ");
        int catId = Validate.validateInt();
        for (int i = 0; i < productService.findAll().size(); i++) {
            if (productService.findAll().get(i).getCategory().getCategoryId()==catId) {
                System.out.println(productService.findAll().get(i));
                break;
            } else {
                System.out.println("Không có mã danh mục bạn đang tìm kiếm! ");
                break;
            }
        }
    }

    private void handleDisplayTopTenProductByQuantityInStock() {
        handleDisplayProduct();
        System.out.println("Danh sách 10 sản phẩm còn số lượng nhiều nhất trong kho: ");
        for (int i=0;i<productService.findAll().size()-1;i++){
            for (int j = 0; j < productService.findAll().size()-1; j++) {
                if (productService.findAll().get(j).getStock()<productService.findAll().get(j+1).getStock()){
                    Product temp = productService.findAll().get(j);
                    productService.findAll().set(j,productService.findAll().get(j+1));
                    productService.findAll().set(j+1,temp);
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            if (productService.findAll().get(i)!=null){
                System.out.println(productService.findAll().get(i));
            }

        }
    }


    private void handleFindProduct() {
        handleDisplayProduct();
        System.out.println("Nhập vào tên sản phẩm bạn muốn tìm kiếm: ");
        String findName = Validate.validateString();
        for (int i = 0; i < productService.findAll().size(); i++) {
            if (productService.findAll().get(i).getProductName().equalsIgnoreCase(findName) && productService.findAll().get(i).isStatus()) {
                System.out.println(productService.findAll().get(i));
                System.out.println("Tìm thành công !");
                break;
            } else {
                System.out.println("Không có sản phẩm bạn đang tìm kiếm!");
                break;
            }
        }
    }
}
