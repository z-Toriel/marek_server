package com.marek.service.impl;

import com.marek.entity.Fans;
import com.marek.mapper.FansMapper;
import com.marek.service.FansService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Byterain
 * @since 2022-12-08
 */
@Service
public class FansServiceImpl extends ServiceImpl<FansMapper, Fans> implements FansService {

}
