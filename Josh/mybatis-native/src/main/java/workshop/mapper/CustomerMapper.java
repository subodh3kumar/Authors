package workshop.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import workshop.entity.Customer;

@Mapper
public interface CustomerMapper {

    @Insert("insert into customer(name, email) values (#{name}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Customer customer);

    @Select("select id, name, email from customer where id = #{id}")
    Customer findById(int id);
}
