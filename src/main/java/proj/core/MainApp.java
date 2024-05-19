package proj.core;

import proj.core.car.*;
import proj.core.fee.GCarFeePolicy;
import proj.core.fee.RCarFeePolicy;
import proj.core.file.ResidentFile;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    static final int DAYS_IN_MONTH = 30;

    static int currentParkingNum = 0;
    static int parkingSpotCount;
    static int residentFee;
    static int tenMinutesFee;
    static int parkingFeeGCarSum = 0;
    static int parkingFeeRCarSum = 0;


    public static void main(String[] args) {
        System.out.println("Starting...");
        Scanner in = new Scanner(System.in);
        parkingSpotCount = getInputValue(in, "Enter the number of parking spots: ");
        residentFee = getInputValue(in, "Enter the monthly parking fee for residents: ");
        tenMinutesFee = getInputValue(in, "Enter the parking fee per 10 minutes: ");

        CarRepository<RCar> rCarRepository = new RCarRepository();
        CarRepository<GCar> gCarRepository = new GCarRepository();
        RCarFeePolicy feeRPolicy = new RCarFeePolicy(residentFee);
        GCarFeePolicy feeGPolicy = new GCarFeePolicy(tenMinutesFee);
        ResidentFile residentFile = new ResidentFile();


        while (true) {
            try {
                System.out.print("> ");
                String input = in.nextLine();
                String[] inputParts = input.split(" ");
                String operation = inputParts[0];

                switch (operation) {
                    case "a":
                        handleAddResidentCar(inputParts, rCarRepository, residentFile);
                        break;
                    case "e":
                        handleEnterCar(inputParts, gCarRepository);
                        break;
                    case "s":
                        displayAllCars(rCarRepository, gCarRepository);
                        break;
                    case "x":
                        handleExitCar(inputParts, gCarRepository, feeGPolicy, rCarRepository);
                        break;
                    case "w":
                        handleWithdrawResidentCar(inputParts, rCarRepository, feeRPolicy);
                        break;
                    case "i":
                        displayIncome(inputParts, rCarRepository, feeRPolicy);
                        break;
                    default:
                        System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("입력 형식이 옳바르지 않습니다. 순서에 맞게 다시 입력해주세요.");
            }
        }
    }

    private static void handleAddResidentCar(String[] inputParts, CarRepository<RCar> rCarRepository, ResidentFile residentFile) {
        if (inputParts.length == 6) {
            Long carId = Long.parseLong(inputParts[1]);
            String phoneNum = inputParts[2];
            int year = Integer.parseInt(inputParts[3]);
            int month = Integer.parseInt(inputParts[4]);
            int date = Integer.parseInt(inputParts[5]);
            int parkingNum = getNextParkingNum();

            if (parkingNum == -1) {
                return;
            }

            RCar newRcar = new RCar(carId, phoneNum, year, month, date, parkingNum);
            boolean save = rCarRepository.save(newRcar);

            if (save) {
                //배정하려는 차가 거주자인지 확인
                boolean matched = false;
                for (String phoneNumber : residentFile.getPhoneNumList()) {
                    if (phoneNumber.equals(newRcar.getPhoneNum())) {
                        matched = true;
                        break;
                    }
                }

                if (matched) {
                    System.out.println(newRcar.getCarId() + " 차량에 주차공간 (#" + parkingNum + "번)이 배정되었습니다!");
                } else {
                    System.out.println("거주자로 등록되지 않은 회원이라, 배정이 불가능합니다. 입차만 가능합니다.");
                }
            } else{
                decrementParkingNum();
            }

        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
        }
    }

    private static void handleEnterCar(String[] inputParts, CarRepository<GCar> gCarRepository) {
        if (inputParts.length == 8) {
            Long carId = Long.parseLong(inputParts[1]);
            String phoneNum = inputParts[2];
            int year = Integer.parseInt(inputParts[3]);
            int month = Integer.parseInt(inputParts[4]);
            int date = Integer.parseInt(inputParts[5]);
            int hour = Integer.parseInt(inputParts[6]);
            int minute = Integer.parseInt(inputParts[7]);
            int parkingNum = getNextParkingNum();

            if (parkingNum == -1) {
                return;
            }

            GCar newGcar = new GCar(carId, phoneNum, year, month, date, hour, minute, parkingNum);
            boolean save = gCarRepository.save(newGcar);

            if (save) {
                System.out.println("일반 차량 " + newGcar.getCarId() + "가(이) 입차하였습니다! 주차공간 " + parkingNum + "번이 배정되었습니다!");
            } else {
                decrementParkingNum();
            }

        } else if (inputParts.length == 7) {
            Long carId = Long.parseLong(inputParts[1]);
            int year = Integer.parseInt(inputParts[2]);
            int month = Integer.parseInt(inputParts[3]);
            int date = Integer.parseInt(inputParts[4]);
            int hour = Integer.parseInt(inputParts[5]);
            int minute = Integer.parseInt(inputParts[6]);

            GCar newGcar = new GCar(carId, year, month, date, hour, minute);
            boolean save = gCarRepository.save(newGcar);

            if (save) {
                System.out.println("거주자 우선 주차차량 " + newGcar.getCarId() + "가(이) 입차하였습니다!");
            }
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
        }
    }

    private static void displayAllCars(CarRepository<RCar> rCarRepository, CarRepository<GCar> gCarRepository) {
        List<RCar> allRCars = rCarRepository.getAllInfo();
        List<GCar> allGCars = gCarRepository.getAllInfo();

        System.out.println("거주자 우선주차 차량");
        for (RCar rCar : allRCars) {
            for (GCar gCar : allGCars) {
                if (rCar.getCarId().equals(gCar.getCarId())) {
                    System.out.println(" " + rCar.getParkingNum() + " " + rCar.getCarId() + " " +
                            rCar.getPhoneNum() + " " + rCar.getYear() + " " + rCar.getMonth() + " " + rCar.getDate());
                }
            }
        }

        System.out.println("일반 차량");
        for (GCar gCar : allGCars) {
            boolean isRCarFound = false; // RCars에 해당 차량이 있는지 여부를 나타내는 플래그

            for (RCar rCar : allRCars) {
                if (gCar.getCarId().equals(rCar.getCarId())) { // RCars에서 차량 ID를 찾았다면
                    isRCarFound = true; // RCars에 해당 차량이 있음을 표시
                    break; // 더 이상 확인할 필요가 없으므로 반복문 탈출
                }
            }
            if (!isRCarFound) { // RCars에 해당 차량이 없다면
                System.out.println(" " + gCar.getParkingNum() + " " + gCar.getCarId() + " " +
                        gCar.getPhoneNum() + " " + gCar.getYear() + " " + gCar.getMonth() + " " +
                        gCar.getDate() + " " + gCar.getHour() + " " + gCar.getMinute());
            }
        }
    }

    private static void handleExitCar(String[] inputParts, CarRepository<GCar> gCarRepository, GCarFeePolicy feeGPolicy, CarRepository<RCar> rCarRepository) {
        try {
            if (inputParts.length == 7) {
                Long carId = Long.parseLong(inputParts[1]);
                int hour = Integer.parseInt(inputParts[5]);
                int minute = Integer.parseInt(inputParts[6]);

                List<RCar> allRCars = rCarRepository.getAllInfo();

                GCar findGCar = gCarRepository.findById(carId);
                gCarRepository.delete(findGCar);

                for (RCar rCar : allRCars) {
                    if (findGCar.getCarId().equals(rCar.getCarId())) {
                        System.out.println("거주자 우선주차 차량 " + findGCar.getCarId() + "가(이) 출차하였습니다!");

                    } else {
                        int parkingMinG = ((hour - findGCar.getHour()) * 60) + (minute - findGCar.getMinute());
                        int parkingFeeG = feeGPolicy.calculateFee(parkingMinG, tenMinutesFee, 0, 0);
                        parkingFeeGCarSum += parkingFeeG;

                        System.out.println("일반 차량 " + findGCar.getCarId() + "가(이) 출차하였습니다!");
                        System.out.println("주차시간: " + parkingMinG + "분");
                        System.out.println("주차요금: " + parkingFeeG + "원");
                    }
                    break;
                }
            } else {
                System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
            }
        } catch (NullPointerException e){
            System.out.println("주차되지 않은 차 입니다. 차 정보를 다시 입력해주세요.");
        }
    }

    private static void handleWithdrawResidentCar(String[] inputParts, CarRepository<RCar> rCarRepository, RCarFeePolicy feeRPolicy) {
        try {
            if (inputParts.length == 5) {
                Long carId = Long.parseLong(inputParts[1]);
                int date = Integer.parseInt(inputParts[4]);

                RCar findRCar = rCarRepository.findById(carId);
                rCarRepository.delete(findRCar);

                int parkingDate = (date - findRCar.getDate() + 1);
                parkingFeeRCarSum += feeRPolicy.calculateFee(0, 0, parkingDate, residentFee);

                System.out.println("거주자 우선주차 차량 " + findRCar.getCarId() + "가(이) 주자공간(#" +
                        findRCar.getParkingNum() + ") 배정을 철회하였습니다!");

            } else {
                System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
            }
        } catch (NullPointerException e){
            System.out.println("배정되지 않은 차 입니다. 차 정보를 다시 입력해주세요.");
        }
    }

    private static void displayIncome(String[] inputParts, CarRepository<RCar> rCarRepository, RCarFeePolicy feeRPolicy) {
        if (inputParts.length == 3) {
            int year = Integer.parseInt(inputParts[1]);
            int month = Integer.parseInt(inputParts[2]);

            List<RCar> allRCars = rCarRepository.getAllInfo();

            for (RCar rCar : allRCars) {
                int parkingDate = (DAYS_IN_MONTH - rCar.getDate() + 1);
                parkingFeeRCarSum += feeRPolicy.calculateFee(0, 0, parkingDate, residentFee);
            }
            int sumAllFee = parkingFeeRCarSum + parkingFeeGCarSum;

            System.out.println("총수입(" + year + "년 " + month + "월): " + sumAllFee);
            System.out.println("  - 거주자 우선주차 차량: " + parkingFeeRCarSum);
            System.out.println("  - 일반 차량: " + parkingFeeGCarSum);

        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
        }
    }

    private static int getInputValue(Scanner in, String prompt) {
        System.out.print(prompt);
        int value = in.nextInt();
        in.nextLine();  // 개행 문자 소비
        return value;
    }

    public static int getNextParkingNum() {
        if(currentParkingNum <= parkingSpotCount -1) {
            return ++currentParkingNum;
        }else {
            System.out.println("주차 공간이 없어, 주차칸 배정과 주차가 불가능합니다! ");
            return -1;
        }
    }

    public static void decrementParkingNum(){
        --currentParkingNum;
    }
}
