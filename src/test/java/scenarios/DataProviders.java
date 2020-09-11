package scenarios;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.User;
import org.testng.annotations.DataProvider;
import setup.BaseTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    @DataProvider(name = "UserProvider")
    public Iterator<Object> provideUser() throws FileNotFoundException {
        Gson gson = new Gson();
        final String fileName = "Users.json";
        Reader reader = new FileReader(BaseTest.getResourceFilePath()+ fileName);
        List<Object> users = gson.fromJson(reader, new TypeToken<List<User>>() {
        }.getType());
        return users.iterator();
    }
    @DataProvider(name = "Search string provider")
    public Object[] provideQueryString() {
       return new Object[]{"Epam"};
    }
}
