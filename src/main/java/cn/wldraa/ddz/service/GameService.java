package cn.wldraa.ddz.service;

import cn.wldraa.ddz.dto.TableDTO;
import cn.wldraa.ddz.dto.UserDTO;

import java.util.List;

/**
 * @author zhangqian
 */
public interface GameService {
    List<TableDTO> tableList();

    void joinTable(UserDTO user, Integer tableId, String seatId);
}
