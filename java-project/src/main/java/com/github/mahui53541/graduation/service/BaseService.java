package com.github.mahui53541.graduation.service;
import com.github.mahui53541.graduation.mapper.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public class BaseService< Mapp extends Mapper< M >, M > {
    @Autowired
    protected Mapp mapper;

    @Autowired
    protected AdminMapper adminMapper;
    @Autowired
    protected FoundMapper foundMapper;
    @Autowired
    protected LostMapper lostMapper;
    @Autowired
    protected ThanksLetterMapper thanksLetterMapper;
    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected UserFoundMapper userFoundMapper;
    @Autowired
    protected UserLostMapper userLostMapper;
    @Autowired
    protected AdminRoleMapper adminRoleMapper;

    public int deleteByExample ( Object example ) {
        return mapper.deleteByExample ( example );
    }

    public int deleteByPrimaryKey ( Object key ) {

        return mapper.deleteByPrimaryKey ( key );
    }

    public int delete ( M record ) {
        return mapper.delete ( record );
    }

    public int insert ( M record ) {
        return mapper.insert ( record );
    }

    public int insertSelective ( M record ) {

        return mapper.insertSelective ( record );
    }

    public List < M > selectAll ( ) {
        return mapper.selectAll ( );
    }

    public List < M > selectByExample ( Object example ) {

        return mapper.selectByExample ( example );
    }

    public List < M > selectByExampleAndRowBounds ( Object example , RowBounds rowBounds ) {

        return mapper.selectByExampleAndRowBounds ( example , rowBounds );
    }

    public M selectByPrimaryKey ( Object key ) {

        return mapper.selectByPrimaryKey ( key );
    }

    public int selectCountByExample ( Object example ) {

        return mapper.selectCountByExample ( example );
    }

    public int selectCount ( M record ) {
        return mapper.selectCount ( record );
    }

    public List < M > select ( M record ) {

        return mapper.select ( record );
    }

    public M selectOne ( M record ) {

        return mapper.selectOne ( record );
    }

    public List < M > selectByRowBounds ( M record , RowBounds rowBounds ) {

        return mapper.selectByRowBounds ( record , rowBounds );
    }

    public int updateByExample (@Param( "record" ) M record , @Param( "example" ) Object example ) {

        return mapper.updateByExample ( record , example );
    }

    public int updateByExampleSelective (@Param( "record" ) M record , @Param( "example" ) Object example ) {

        return mapper.updateByExampleSelective ( record , example );
    }

    public int updateByPrimaryKey ( M record ) {

        return mapper.updateByPrimaryKey ( record );
    }

    public int updateByPrimaryKeySelective ( M record ) {

        return mapper.updateByPrimaryKeySelective ( record );
    }

    public boolean existsWithPrimaryKey ( Object key ) {

        return true;
    }

    public boolean isNull ( Object text ) {

        boolean flag = false;
        if ( text == null || text.equals ( "null" ) || text.equals ( "undefined" ) ) {
            flag = true;
        }
        return flag;
    }

    public String getText ( Object object ) {

        String[] text = new String[ 1 ];
        if ( object != null ) {
            text[ 0 ] = object.toString ( );
        } else {
            text[ 0 ] = "无";
        }
        return text[ 0 ];
    }
}
