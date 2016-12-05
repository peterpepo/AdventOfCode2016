package day05;

public class Md5Sync {

    int counter = 0;
    static Md5Sync self = new Md5Sync();

    private Character[] password = new Character[8];

    public String getPassword() {
        StringBuilder resultPassword = new StringBuilder();
        for (Character c : password) {
            resultPassword.append(c);
        }
        return resultPassword.toString();
    }

    public void setCharacter(int position, Character c) {
        if (position > 7) {
            return;
        }

        if (password[position] == null) {
            password[position] = c;
        }
    }

    public boolean isPasswordComplete() {
        for (Character c : password) {
            if (c == null) {
                return false;
                
            }
        }
        return true;
    }

    private Md5Sync() {

    }

    public static Md5Sync getInstance() {
        return self;
    }

    public int getCounter() {
        return counter++;
    }

}
