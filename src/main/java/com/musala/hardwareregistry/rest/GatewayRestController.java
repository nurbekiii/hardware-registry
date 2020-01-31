package com.musala.hardwareregistry.rest;

import com.musala.hardwareregistry.entity.Equipment;
import com.musala.hardwareregistry.entity.Gateway;
import com.musala.hardwareregistry.pojo.EquipmentRequest;
import com.musala.hardwareregistry.pojo.GatewayRequest;
import com.musala.hardwareregistry.repo.EquipmentRepo;
import com.musala.hardwareregistry.repo.GatewayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author NIsaev on 07.12.2019
 */

@RestController
@RequestMapping("/gateway")
public class GatewayRestController {

    @Autowired
    private GatewayRepo gatewayRepo;

    @Autowired
    private EquipmentRepo equipmentRepo;

    @PostMapping("/create")
    public GatewayRequest createGateway(@Valid @RequestBody GatewayRequest request) throws Exception {
        if (request == null) {
            return null;
        }

        if (!validate(request.getAddress()))
            throw new Exception("Invalid value for IP Address is provided");

        return saveGateway(request);
    }

    @GetMapping("/get/{id}")
    public GatewayRequest findByName(@PathVariable UUID id)  {
        Optional<Gateway> op =  gatewayRepo.findById(id);
        Gateway gtw = op.get();
        if(gtw != null) {
            List<Equipment> equipments = equipmentRepo.getEquipmentByGateway(gtw.getId());
            return gatewayToRequest(gtw, equipments);
        }

        return null;
    }

    @GetMapping("/find")
    public List<GatewayRequest> findByName(@RequestParam String name)  {
        List<Gateway> list =  gatewayRepo.findAllByNameContainsOrderByName(name);
        return prepareGatewayList(list);
    }

    @GetMapping("/all")
    public List<GatewayRequest> createGateway() {
        List<Gateway> list = gatewayRepo.findAll();
        return prepareGatewayList(list);
    }

    List<GatewayRequest> prepareGatewayList(List<Gateway> list){
        if (list != null && !list.isEmpty()) {
            List<GatewayRequest> gatewayList = new ArrayList<>();
            for (Gateway gtw : list) {
                List<Equipment> equipments = equipmentRepo.getEquipmentByGateway(gtw.getId());

                GatewayRequest gr = gatewayToRequest(gtw, equipments);
                gatewayList.add(gr);
            }
            return gatewayList;
        }

        return null;
    }

    private GatewayRequest saveGateway(GatewayRequest request) {
        //fill Gateway data
        Gateway gtw = new Gateway(UUID.randomUUID(), request.getName(), request.getAddress());

        //save Gateway
        gtw = gatewayRepo.save(gtw);

        List<Equipment> list = new ArrayList<>();
        //if equipments are not empty
        if (request.getEquipments() != null) {
            //go through  equipments list
            for (EquipmentRequest equip : request.getEquipments()) {

                //fill Equipment data
                Equipment equipment = new Equipment();
                equipment.setVendor(equip.getVendor());
                equipment.setStatus(equip.getStatus());

                //set current time, date
                //equipment.setCreated(LocalDateTime.now());
                equipment.setCreated(equip.getCreated());
                equipment.setGateway(gtw.getId());

                //collect equipments in list
                list.add(equipment);
            }

            //save all equipments at once
            list = equipmentRepo.saveAll(list);

            //gtw.setEquipments(list);
        }

        //return saved gateways, equipments
        return gatewayToRequest(gtw, list);

    }

    private GatewayRequest gatewayToRequest(Gateway gtw, List<Equipment> list) {
        GatewayRequest req = new GatewayRequest(gtw.getId(), gtw.getName(), gtw.getAddress());
        List<EquipmentRequest> list2 = new ArrayList<>();
        if (list != null && !list.isEmpty())
            for (Equipment eq : list) {
                EquipmentRequest req2 = new EquipmentRequest(eq.getId(), eq.getVendor(), eq.getCreated(), eq.getStatus());

                list2.add(req2);
            }
        req.setEquipments(list2);
        return req;
    }

    public boolean validate(final String ip) {
        String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";

        return ip.matches(PATTERN);
    }

}
