package ra.View.account.admin;

import ra.config.Config;
import ra.config.Validate;
import ra.model.Category;
import ra.service.category.CategoryServiceIMPL;
import ra.service.category.ICategoryService;

import static ra.config.Color.RESET;

public class CategoryManagement {
    ICategoryService categoryService = new CategoryServiceIMPL();

    public void menu() {
        do {
            System.out.println("\033[1;94m╔═══════════════════════ QUẢN LÝ DANH MỤC ═════════════════════╗");
            System.out.println("\033[1;94m║                                                              ║");
            System.out.println("\033[1;94m║              \033[1;97m1. Hiển thị tất cả danh mục" + RESET + "\033[1;94m                     ║");
            System.out.println("\033[1;94m║              \033[1;97m2. Thêm mới 1 hoặc nhiều danh mục " + RESET + "\033[1;94m              ║");
            System.out.println("\033[1;94m║              \033[1;97m3. Tìm kiếm danh mục theo tên" + RESET + "\033[1;94m                   ║");
            System.out.println("\033[1;94m║              \033[1;97m4. Sửa thông tin danh mục" + RESET + "\033[1;94m                       ║");
            System.out.println("\033[1;94m║              \033[1;97m5. Ẩn thông tin danh mục theo mã danh mục" + RESET + "\033[1;94m       ║");
            System.out.println("\033[1;94m║              \033[1;97m0. Thoát" + RESET + "\033[1;94m                                        ║");
            System.out.println("\033[1;94m║                                                              ║");
            System.out.println("\033[1;94m╚══════════════════════════════════════════════════════════════╝" + RESET);
            System.out.print("Mời lựa chọn (1/2/3/4/5/6/0): ");
            switch (Validate.validateInt()) {
                case 1:
                    handleDisplayCategories();
                    break;
                case 2:
                    handleAddNewCategory();
                    break;
                case 3:
                    handleFindCategoryByCategoryName();
                    break;
                case 4:
                    handleEditCategoryById();
                    break;
                case 5:
                    handleHiddenCategoryByCategoryId();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (true);
    }

    private void handleHiddenCategoryByCategoryId() {
        for (Category cat : categoryService.findAll()) {
            if (cat.isStatus()) {
                System.out.println(cat);
            }
        }
        System.out.println("Nhập mã danh mục bạn muốn ẩn: ");
        int hiddenNum = Validate.validateInt();
        for (int i = 0; i < categoryService.findAll().size(); i++) {
            if (categoryService.findAll().get(i).getCategoryId() == hiddenNum) {
                System.out.println("Danh mục bạn muốn ẩn là: " + categoryService.findAll().get(i));
                System.out.println("Bạn có chắc muốn ẩn không? ");
                System.out.println("1. Có");
                System.out.println("2. Không");
                System.out.println("Mời chọn: ");
                switch (Validate.validateInt()) {
                    case 1:
                        categoryService.findAll().get(i).setStatus(!categoryService.findAll().get(i).isStatus());
                        System.out.println("Ẩn thành công");
                        categoryService.updateData();
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Không có lựa chọn này! ");
                        break;
                }
            }
        }
    }


    private void handleEditCategoryById() {
        handleDisplayCategories();
        System.out.println("Nhập mã danh mục bạn muốn sửa: ");
        int num = Validate.validateInt();
        for (Category cat : categoryService.findAll()) {
            if (cat.getCategoryId() == num) {
                System.out.println(cat);
                boolean isDone = false;
                while (!isDone) {
                    System.out.println("Chọn thông tin danh mục bạn muốn sửa: ");
                    System.out.println("1. Tên danh mục: ");
                    System.out.println("2. Mô tả: ");
                    System.out.println("3. Trạng thái: ");
                    System.out.println("0. Quay lại ");
                    switch (Validate.validateInt()) {
                        case 1:
                            System.out.println("Tên danh mục hiện tại là: " + cat.getCategoryName());
                            System.out.println("Nhập tên mới: ");
                            cat.setCategoryName(Validate.validateString());
                            System.out.println("Tên sau khi thay đổi là: " + cat.getCategoryName());
                            categoryService.updateData();
                            break;
                        case 2:
                            System.out.println("Mô tả hiện tại là: " + cat.getDescription());
                            System.out.println("Nhập mô tả mới: ");
                            cat.setDescription(Validate.validateString());
                            System.out.println("Mô tả sau khi sửa là: " + cat.getDescription());
                            categoryService.updateData();
                            break;
                        case 3:
                            System.out.println("Trạng thái hiện tại là: " + cat.isStatus());
                            System.out.println("Bạn có muốn thay đổi trạng thái không? ");
                            System.out.println("1. Có");
                            System.out.println("2. Không");
                            switch (Validate.validateInt()) {
                                case 1:
                                    cat.setStatus(!cat.isStatus());
                                    System.out.println("Đã thay đổi trạng thái thành " + cat.isStatus());
                                    categoryService.updateData();
                                    break;
                                case 2:
                                    break;
                            }
                            break;
                        case 0:
                            isDone = true;
                            return;
                        default:
                            System.out.println("Không có lựa chọn này!");
                            break;
                    }
                }
                categoryService.save(cat);
            }
        }
        System.out.println("Thay đổi thành công !");
    }

    private void handleFindCategoryByCategoryName() {
        handleDisplayCategories();
        System.out.println("Nhập tên danh mục bạn cần tìm kiếm: ");
        String findCatName = Validate.validateString();
        for (Category cat : categoryService.findAll()) {
            if (cat.getCategoryName().toLowerCase().contains(findCatName.toLowerCase())) {
                System.out.println(cat);
            }
        }
        System.out.println("Tìm kiếm thành công !");
    }

    private void handleAddNewCategory() {
        System.out.println("Nhập số lượng danh mục bạn muốn thêm mới: ");
        int n = Validate.validateInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập danh mục thứ: " + (i + 1));
            Category cat = new Category();
            cat.setCategoryId(categoryService.getNewId());
            System.out.println("Nhập tên danh mục: ");
            cat.setCategoryName(Validate.validateString());
//            String catName = Validate.validateString();
//            while (true) {
//            boolean isExist = false;
//                System.out.println("Nhập tên danh mục: ");
//                String catName = Validate.validateString();
//                for (Category cate : categoryService.findAll()) {
//                    if (cate.getCategoryName().equalsIgnoreCase(catName)) {
//                        isExist = true;
//                    }
//                }
//                if (isExist) {
//                    System.out.println("Danh mục này đã tồn tại. Mời nhập lại: ");
//                }else {
//                    cat.setCategoryName(catName);
//                    break;
//                }
//            }
//

//            String catName;
//            while (true) {
//                catName = Validate.validateString();
//                if (catName == null) {
//                    System.out.println("Giá trị không hợp lệ. Mời nhập lại: ");
//                } else if (categoryService.existCategoryName(catName)) {
//                    System.out.println("Danh mục đã tồn tại. Mời nhập lại: ");
//                } else {
//                    break;
//                }
//            }
            System.out.println("Nhập mô tả: ");
            cat.setDescription(Validate.validateString());
            categoryService.save(cat);
            System.out.println("Thêm mới danh mục thành công");
        }
    }

    private void handleDisplayCategories() {
        categoryService.findAll().sort((c1, c2) -> c2.getCategoryId() - c1.getCategoryId());
        for (Category cat : categoryService.findAll()) {
            System.out.println(cat);
        }
    }
}
