

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private int ID;
    private String username;
    private String level;
    private String registrationTime;
    private double totalAmount;
    private int totalTimes;
    private String phoneNumber;
    private String email;
    private int password;

    public User(int ID, String username, String level, String registrationTime, double totalAmount, int totalTimes, String phoneNumber, String email,int password) {
        this.ID = ID;
        this.username = username;
        this.level = level;
        this.registrationTime = registrationTime;
        this.totalAmount = totalAmount;
        this.totalTimes = totalTimes;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password=password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(int totalTimes) {
        this.totalTimes = totalTimes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getPassword() {
        return password;
    }
}

class Movie {
    private String title;
    private String director;
    private String actor;
    private String synopsis;
    private int duration;

    public Movie(String title, String director, String actor, String synopsis, int duration) {
        this.title = title;
        this.director = director;
        this.actor = actor;
        this.synopsis = synopsis;
        this.duration = duration;
    }
    public Movie(String title) {
        this.title = title;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    public static Movie parseMovieInfo(String movieInfo) {
        String[] fields = movieInfo.split(",");
        String title = fields[0].trim();
        String director = "";
        String actor = "";
        String synopsis = "";
        int duration = 0;

        if (fields.length >= 2) {
            director = fields[1].trim();
        }
        if (fields.length >= 3) {
            actor = fields[2].trim();
        }
        if (fields.length >= 4) {
            synopsis = fields[3].trim();
        }
        if (fields.length >= 5) {
            try {
                duration = Integer.parseInt(fields[4].trim());
            } catch (NumberFormatException e) {
                // 处理无效的整数字段
                System.out.println("Invalid duration value: " + fields[4].trim());
            }
        }

        return new Movie(title, director, actor, synopsis, duration);
    }



}

class Session {
    private Movie movie;
    private int theater;
    private String time;
    private double price;

    public Session(Movie movie, int theater, String time, double price) {
        this.movie = movie;
        this.theater = theater;
        this.time = time;
        this.price = price;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getTheater() {
        return theater;
    }

    public void setTheater(int theater) {
        this.theater = theater;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public static Session parseSessionInfo(String sessionInfo) {
        String[] fields = sessionInfo.split(",");
        String movieTitle = fields[0].trim();
        int theater = Integer.parseInt(fields[1].trim());
        String time = fields[2].trim();
        double price = Double.parseDouble(fields[3].trim());

        Movie movie = new Movie(movieTitle);

        return new Session(movie, theater, time, price);
    }

}

class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void resetUserPassword(User user, int newPassword) {
        user.setPassword(newPassword);
        System.out.println("用户密码已重置为：" + newPassword);
    }

    public void listAllUsers(List<User> userList) {
        System.out.println("所有用户：");
        for (User user : userList) {
            System.out.println("用户名：" + user.getUsername());
            System.out.println("用户ID；"+ user.getID());
            System.out.println("等级：" + user.getLevel());
            System.out.println("电话号码：" + user.getPhoneNumber());
            System.out.println("邮箱：" + user.getEmail());
            System.out.println("--------------------");
        }
    }

    public void deleteUser(   List<User> userList ,User user) {
        userList.remove(user);
        System.out.println("用户已成功删除");
    }

    public void searchUser( List<User> userList,String query) {
        List<User> searchResults = new ArrayList<>();

        for (User user : userList) {
            if (user.getUsername().contains(query) || user.getEmail().contains(query)) {
                searchResults.add(user);
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("未找到符合条件的用户");
        } else {
            System.out.println("搜索结果:");
            for (User user : searchResults) {
                System.out.println(user.getUsername() + " - " + user.getEmail());
            }
        }
    }


    public void logout() {


        System.out.println("管理员已成功注销");
    }
}

class Manager {
    private String username;
    private String password;

    public Manager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void listAllMovies(List<Movie> movieList) {
        System.out.println("所有电影：");
        for (Movie movie : movieList) {
            System.out.println("电影名称：" + movie.getTitle());
            System.out.println("导演：" + movie.getDirector());
            System.out.println("演员：" + movie.getActor());
            System.out.println("简介：" + movie.getSynopsis());
            System.out.println("时长：" + movie.getDuration());
            System.out.println("--------------------");
        }
    }

    public void addMovie(List<Movie> movieList,Movie movie) {
        movieList.add(movie);
    }

    public void updateMovie(List<Movie> movieList,Movie movie) {
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getTitle().equals(movie.getTitle())) {
                movieList.set(i, movie);
                break;
            }
        }
    }

    public void deleteMovie(List<Movie> movieList, String movieTitle) {
        for (Movie movie : movieList) {
            if (movie.getTitle().equalsIgnoreCase(movieTitle)) {
                movieList.remove(movie);
                return; // 找到匹配的电影并删除后，立即返回
            }
        }
        System.out.println("未找到匹配的电影。");
    }
    public void searchMovie(List<Movie> movieList, String movieName) {
        for (Movie movie : movieList) {
            if (movie.getTitle().equalsIgnoreCase(movieName)) {
                System.out.println("电影名称：" + movie.getTitle());
                System.out.println("导演：" + movie.getDirector());
                System.out.println("演员：" + movie.getActor());
                System.out.println("简介：" + movie.getSynopsis());
                System.out.println("时长：" + movie.getDuration());
                System.out.println("--------------------");
                return; // 找到电影后立即返回，避免继续遍历
            }
        }
        System.out.println("未找到匹配的电影信息。");
    }

    public void addSession(List<Session> sessionList,Session session) {
        sessionList.add(session);
    }

    public void updateSession(List<Session>sessionList,Session session) {

    }

    public void deleteSession(List<Session> sessionList,String movieTitle) {
        for(Session session :sessionList ){
            if(session.getMovie().getTitle().equals(movieTitle)){
                sessionList.remove(session);
                return;
            }
            }


    }

    public void listAllSessions(List<Session> sessionList) {
        for (Session session : sessionList) {
            System.out.println("电影名称：" + session.getMovie().getTitle());
            System.out.println("放映厅：" + session.getTheater());
            System.out.println("时间：" + session.getTime());
            System.out.println("价格：" + session.getPrice());
            System.out.println("--------------------");
        }
    }

    public void logout() {
        // 重置当前用户会话或状态
        // 并执行任何必要的清理任务
        // 例如关闭数据库连接或清除缓存

        System.out.println("经理已成功注销");
    }
}

public class CinemaManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String username;
        String password;
        int choice;

        // 创建示例用户、电影和场次数据
        User user1 = new User(1, "张三", "黄金会员", "2021-01-01", 100.0, 5, "1234567890", "zhangsan@example.com",11132222);
        User user2 = new User(2, "李四", "白银会员", "2021-02-01", 50.0, 3, "0987654321", "lisi@example.com",11223344);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        Movie movie1 = new Movie("电影1", "导演1", "演员1", "简介1", 120);
        Movie movie2 = new Movie("电影2", "导演2", "演员2", "简介2", 90);
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie1);
        movieList.add(movie2);

        Session session1 = new Session(movie1, 1, "2021-03-01 18:00", 10.0);
        Session session2 = new Session(movie2, 2, "2021-03-01 20:00", 8.0);
        List<Session> sessionList = new ArrayList<>();
        sessionList.add(session1);
        sessionList.add(session2);

        // 登录
        System.out.print("用户名：");
        username = scanner.nextLine();
        System.out.print("密码：");
        password = scanner.nextLine();

        if (username.equals("admin") && password.equals("ynuinfo#777")) {
            Admin admin = new Admin(username, password);

            // 管理员菜单
            do {
                System.out.println("管理员菜单：");
                System.out.println("1. 修改密码");
                System.out.println("2. 重置用户密码");
                System.out.println("3. 列出所有用户");
                System.out.println("4. 删除用户");
                System.out.println("5. 搜索用户");
                System.out.println("6. 注销");
                System.out.print("请选择操作：");
                choice = scanner.nextInt();
                scanner.nextLine();  // 消费换行符

                switch (choice) {
                    case 1:
                        System.out.print("请输入新密码：");
                        String newPassword = scanner.nextLine();
                        admin.changePassword(newPassword);
                        System.out.println("密码修改成功。");
                        break;
                    case 2:
                        System.out.print("请输入用户ID：");
                        int userID = scanner.nextInt();
                        scanner.nextLine();  // 消费换行符
                        System.out.print("请输入新密码：");
                        int userNewPassword = Integer.parseInt(scanner.nextLine());
                        User user = userList.stream()
                                .filter(u -> u.getID() == userID)
                                .findFirst()
                                .orElse(null);
                        if (user != null) {
                            admin.resetUserPassword(user, userNewPassword);
                            System.out.println("用户密码重置成功。");
                        } else {
                            System.out.println("找不到用户。");
                        }
                        break;
                    case 3:
                        admin.listAllUsers(userList);
                        break;
                    case 4:
                        System.out.print("请输入用户ID：");
                        int deleteUserID = scanner.nextInt();
                        scanner.nextLine();  // 消费换行符
                        User deleteUser = userList.stream()
                                .filter(u -> u.getID() == deleteUserID)
                                .findFirst()
                                .orElse(null);
                        if (deleteUser != null) {
                            System.out.print("确定要删除用户" + deleteUser.getUsername() + "吗？（Y/N）：");
                            String confirmation = scanner.nextLine();
                            if (confirmation.equalsIgnoreCase("Y")) {
                                admin.deleteUser(userList,deleteUser);
                                System.out.println("用户删除成功。");
                            } else {
                                System.out.println("用户删除取消。");
                            }
                        } else {
                            System.out.println("找不到用户。");
                        }
                        break;
                    case 5:
                        System.out.print("请输入搜索关键词：");
                        String query = scanner.nextLine();
                        admin.searchUser( userList,query);
                        break;
                    case 6:
                        admin.logout();
                        break;
                    default:
                        System.out.println("无效选择，请重试。");
                        break;
                }
            } while (choice != 6);

        } else if (username.equals("manager") && password.equals("password")) {
            Manager manager = new Manager(username, password);

            // 经理菜单
            do {
                System.out.println("经理菜单：");
                System.out.println("1. 修改密码");
                System.out.println("2. 列出所有电影");
                System.out.println("3. 添加电影");
                System.out.println("4. 更新电影");
                System.out.println("5. 删除电影");
                System.out.println("6. 搜索电影");
                System.out.println("7. 添加场次");
                System.out.println("8. 更新场次");
                System.out.println("9. 删除场次");
                System.out.println("10. 列出所有场次");
                System.out.println("11. 注销");
                System.out.print("请选择操作：");
                choice = scanner.nextInt();
                scanner.nextLine(); // 消费换行符

                switch (choice) {
                    case 1:
                        System.out.print("请输入新密码：");
                        String newPassword = scanner.nextLine();
                        manager.changePassword(newPassword);
                        System.out.println("密码修改成功。");
                        break;
                    case 2:
                        manager.listAllMovies(movieList);
                        break;
                    case 3:
                        System.out.print("请输入电影信息：");
                        String movieInfo = scanner.nextLine();
                        Movie movie =   Movie.parseMovieInfo(movieInfo); // 解析电影信息
                        manager.addMovie(movieList,movie);
                        System.out.println("电影添加成功。");
                        break;
                    case 4:
                        System.out.print("请输入电影信息：");
                        String movieInfo1 = scanner.nextLine();
                        Movie updatedMovie = Movie.parseMovieInfo(movieInfo1); // 解析电影信息
                        manager.updateMovie(movieList,updatedMovie);
                        System.out.println("电影信息更新成功。");
                        break;
                    case 5:
                        System.out.print("请输入电影片名：");
                        String movieTitle = scanner.nextLine();
                        manager.deleteMovie(movieList, movieTitle);
                        System.out.println("电影删除成功。");
                        break;
                    case 6:
                        System.out.print("请输入电影名：");
                        String movieName = scanner.nextLine();
                        manager.searchMovie(movieList, movieName);
                        break;
                    case 7:
                        System.out.print("请输入场次信息：");
                        String sessionInfo = scanner.nextLine();
                        Session session = Session.parseSessionInfo(sessionInfo); // 解析场次信息
                        manager.addSession(sessionList,session);
                        System.out.println("场次添加成功。");
                        break;
                    case 8:
                        System.out.print("请输入场次信息：");
                        String sessionInfo3 = scanner.nextLine();
                        Session updatedSession = Session.parseSessionInfo(sessionInfo3); // 解析场次信息
                        manager.updateSession(sessionList,updatedSession);
                        System.out.println("场次信息更新成功。");
                        break;
                    case 9:
                        System.out.print("请输入场次放映的电影：");
                        String sessionInfo4 = scanner.nextLine();

                        manager.deleteSession(sessionList,sessionInfo4);
                        System.out.println("场次删除成功。");
                        break;
                    case 10:
                        manager.listAllSessions(sessionList);
                        break;
                    case 11:
                        manager.logout();
                        break;
                    default:
                        System.out.println("无效选择，请重试。");
                        break;
                }
            } while (choice != 11);

        } else {
            System.out.println("无效的用户名或密码，请重试。");
        }

        scanner.close();
    }
}