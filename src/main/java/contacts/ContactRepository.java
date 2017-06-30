package contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Steve on 2017/7/1.
 */

@Repository
public class ContactRepository
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepository(JdbcTemplate jdbc)
    {
        this.jdbcTemplate = jdbc;
    }

    public List<Contact> findAll()
    {
        String jql = " select id, firstName, lastName, phoneNumber, emailAddress " + " from contacts order by lastName ";
        RowMapper<Contact> rowMapper = new RowMapper<Contact>()
        {
            @Override
            public Contact mapRow(ResultSet resultSet, int rowNum) throws SQLException
            {
                Contact contact = new Contact();
                contact.setId(resultSet.getLong(1));
                contact.setFirstName(resultSet.getString(2));
                contact.setLastName(resultSet.getString(3));
                contact.setPhoneNumber(resultSet.getString(4));
                contact.setEmailAddress(resultSet.getString(5));
                return contact;
            }
        };
        return jdbcTemplate.query(jql, rowMapper);
    }

    public void save(Contact contact)
    {
        String jql = " insert into contacts " + " (firstName, lastName, phoneNumber, emailAddress) " + " values (?, ?, ?, ?) ";
        jdbcTemplate.update(jql, contact.getFirstName(), contact.getLastName(),
                    contact.getPhoneNumber(), contact.getEmailAddress());
    }
}
