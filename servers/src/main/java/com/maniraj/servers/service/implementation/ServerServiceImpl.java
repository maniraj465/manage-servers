package com.maniraj.servers.service.implementation;

import com.maniraj.servers.enumeration.Status;
import com.maniraj.servers.model.Server;
import com.maniraj.servers.repository.ServerRepository;
import com.maniraj.servers.service.ServerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

import static java.lang.Boolean.*;
import static org.springframework.data.domain.PageRequest.*;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

    @Autowired
    private final ServerRepository serverRepository;

    @Override
    public Server create(Server server) {
        log.info("Saving a new server {}", server.getName());
        server.setImageUrl(buildServerImageUrl(server));
        log.info("Server image URL: {}", server.getImageUrl());
        return serverRepository.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(5000) ? Status.UP : Status.DOWN);
        server.setImageUrl(buildServerImageUrl(server));
        return serverRepository.save(server);
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return serverRepository.findAll(of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id: {}", id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by id: {}", id);
        serverRepository.deleteById(id);
        return TRUE;
    }

    private String buildServerImageUrl(Server server) {
//        String imageUrl = Paths.get(System.getProperty("user.home")) + "\\Documents\\GitHub\\manage-servers\\servers\\src\\main\\resources\\static\\images\\" + server.getType() + "_" + server.getStatus() + ".png";
        String imageUrl = "/servers/images/" + server.getType() + "_" + server.getStatus() + ".png";
        log.info("Setting server image URL: {}", imageUrl);
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(imageUrl).toUriString();
//        return imageUrl;
    }

}
