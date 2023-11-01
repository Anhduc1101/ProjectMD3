package ra.View.account.user;

import ra.View.Home;
import ra.config.Config;
import ra.config.Validate;
import ra.model.Users;

import java.sql.SQLOutput;

import static ra.config.Color.RED;
import static ra.config.Color.RESET;

public class MenuUser {
    public void menuUser() {
        do {
            System.out.println("Xin chào : " + RED + new Config<Users>().readFile(Config.URL_USER_LOGIN).getName() + RESET);
            System.out.println("\033[1;94m╔═════════════════ TRANG USER ═══════════════════╗");
            System.out.println("\033[1;94m║                                                ║");
            System.out.println("\033[1;94m║              \033[1;97m1. Trang chủ"+RESET+"\033[1;94m                      ║");
            System.out.println("\033[1;94m║              \033[1;97m2. Chi tiết sản phẩm"+RESET+"\033[1;94m              ║");
            System.out.println("\033[1;94m║              \033[1;97m3. Giỏ hàng"+RESET+"\033[1;94m                       ║");
            System.out.println("\033[1;94m║              \033[1;97m4. Trang liên hệ"+RESET+"\033[1;94m                  ║");
            System.out.println("\033[1;94m║              \033[1;97m5. Thông tin cá nhân"+RESET+"\033[1;94m              ║");
            System.out.println("\033[1;94m║              \033[1;97m6. Lịch sử đơn hàng"+RESET+"\033[1;94m               ║");
            System.out.println("\033[1;94m║              \033[1;97m0. Đăng xuất"+RESET+"\033[1;94m                      ║");
            System.out.println("\033[1;94m║                                                ║");
            System.out.println("\033[1;94m╚════════════════════════════════════════════════╝"+RESET);
            System.out.print("Mời lựa chọn (1/2/3/4/5/6/0): ");
            switch (Validate.validateInt()) {
                case 1:
                    new UserHomePage().menu();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 0:
                    new Config<Users>().writeFile(Config.URL_USER_LOGIN, null);
                    new Home().menu();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        } while (true);
    }
}
