package com.back.domain.system.controller;

import com.back.domain.constant.GuideMsg;
import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.Scanner;

import static com.back.domain.system.controller.SystemController.CmdMsg.*;

// 입력받을 명령어
public class SystemController {
    Scanner sc;
    private final WiseSayingController wiseSayingController;
    static int qid = -1; // 다른 방법?
    public enum CmdMsg {
        NONE, DELETE, EDIT, EXIT, REGISTER, VIEW;
        public static CmdMsg from(String input) {
            // 삭제 및 수정
            if (chkFormat(input)) {
                switch (input.substring(0, 2)) {
                    case "삭제" -> { return DELETE;}
                    case "수정" -> { return EDIT;}
                }
            }
            // 종료, 등록, 목록
            return switch (input) {
                case "종료" -> EXIT;
                case "등록" -> REGISTER;
                case "목록" -> VIEW;
                default -> NONE;
            };
        }
        private static boolean chkFormat(String input) {
            if (input.length()>=7 && input.startsWith("?id=", 2)) {
                try {
                    qid = Integer.parseInt(input.substring(6));
                } catch(Error e) { return false;}
                return true;
            } else return false;
        }
    }

    public SystemController(Scanner sc) {
        this.sc = sc;
        // SystemController에서 프로그램 시작 전에 객체들을 준비해두는 것이 도구가 사용자 명령에 맞게 사용하기 쉽다고 함
        WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();
        WiseSayingService wiseSayingService = new WiseSayingService(wiseSayingRepository);
        this.wiseSayingController = new WiseSayingController(sc, wiseSayingService);
    }

    public void run() {
        while (true) {
            System.out.print(GuideMsg.CMD.getValue());
            String input = sc.nextLine();
            CmdMsg currentCmd = from(input);
            switch (currentCmd) {
                case NONE -> System.out.print(GuideMsg.ERROR.getValue());
                case EXIT -> { return;}
                case REGISTER -> wiseSayingController.execute(REGISTER, -1);
                case VIEW -> wiseSayingController.execute(VIEW, -1);
                case DELETE -> wiseSayingController.execute(DELETE, qid);
                case EDIT -> wiseSayingController.execute(EDIT, qid);
            }
        }
    }
}