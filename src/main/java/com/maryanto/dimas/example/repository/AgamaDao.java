package com.maryanto.dimas.example.repository;

import com.maryanto.dimas.example.entity.Agama;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class AgamaDao {

    @Autowired
    private NamedParameterJdbcTemplate parameterJdbcTemplate;

    public Agama findById(Integer id) throws EmptyResultDataAccessException {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("agamaId", id);

        String query = "select id, name, description, create_by, create_by, last_update_date, last_update_by from master_agama where id = :agamaId";
        Agama agama = this.parameterJdbcTemplate.queryForObject(query, params, new AgamaRowMapper());
        return agama;
    }

    public List<Agama> findAll() {
        String query = "select id, name, description, create_date, create_by, last_update_date, last_update_by from master_agama";
        List<Agama> list = this.parameterJdbcTemplate.query(query, new AgamaRowMapper());
        return list;
    }

    @Transactional
    public Agama save(Agama value) throws EmptyResultDataAccessException {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", value.getName());
        params.addValue("description", value.getDescription());
        params.addValue("createdBy", value.getCreatedBy());

        String query = "insert into master_agama(name, description, create_by) values (:name, :description, :createdBy)";
        int rowUpdated = this.parameterJdbcTemplate.update(query, params, keyHolder);
        Long primaryKey = (Long) keyHolder.getKey();
        return findById(primaryKey.intValue());
    }

    @Transactional
    public Agama update(Agama value) throws EmptyResultDataAccessException {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", value.getName());
        params.addValue("description", value.getDescription());
        params.addValue("updatedBy", value.getLastUpdateBy());
        params.addValue("id", value.getId());

        String query = "update master_agama set name = :name, description = :description, last_update_date = now(), last_update_by = :updatedBy where id = :id";
        this.parameterJdbcTemplate.update(query, params);
        return findById(value.getId());
    }

    @Transactional
    public boolean removeById(Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        String query = "delete from master_agama where id = :id";
        return this.parameterJdbcTemplate.update(query, params) >= 1;
    }

    private class AgamaRowMapper implements RowMapper<Agama> {
        @Override
        public Agama mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Agama(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getTimestamp("create_date"),
                    rs.getString("create_by"),
                    rs.getTimestamp("last_update_date"),
                    rs.getString("last_update_by")
            );
        }
    }
}
