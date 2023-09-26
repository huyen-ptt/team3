/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package khutro.aptech.group3.dao;

import java.util.List;
import khutro.aptech.group3.model.RoomModel;

/**
 *
 * @author CLD
 */
public interface IRoomDao {

    List<RoomModel> getAllRooms(); //lấy ra tất cả thông tin bảng room

    boolean insertRoom(RoomModel roomModel);
}

