import java.util.Scanner;

class BankAccount{

    String accountNumber= "ABCDEF123456";
    int pin= 1234;
    float accountBalance= 55000.2f;
    int transaction= 0;
    String transactionHistory="";

    Scanner sc= new Scanner(System.in);
    public boolean login(){
        boolean isLogin= false;
        System.out.println("Please Enter the User Id (Account No.): ");
        String curraccountNo= sc.nextLine() ;
        if(curraccountNo.equals(accountNumber)){
            System.out.println("Enter PIN: ");
            int userPin= sc.nextInt();
            if(userPin==pin){
                isLogin=true;
            }
            else{
                System.out.println("Invalid PIN");
                isLogin= false;
            }
        }else{
            System.out.println("Incorrect Account Number ");
            isLogin= false;
        }
        return isLogin;
    }
    public void transactionHistory(){
        if(transaction==0){
            System.out.println("No transactions are done");
        }
        else{
            System.out.println(transactionHistory);
        }
    }

    public void withdraw(){
        System.out.println("Enter the amount you want to withdraw  Rs. ");
        float amount;
        amount=sc.nextFloat();
        if(amount>accountBalance){
            System.out.println("Insufficient balance! ");
        }else{
            if(amount<=20000){
                accountBalance-= amount;
                transaction++;
                String str= amount+"Rs Withdrawn.\n";
                transactionHistory= transactionHistory.concat(str);
                System.out.println("Amount successfully withdrawn...");
            }else{
                System.out.println("Limit amount 20000");
            }
        }

    }
    public void deposit(){
        System.out.println("Enter the amount you want to deposit ");
        float amount;
        amount= sc.nextFloat();
        if(amount<=20000){
            accountBalance+=amount;
            transaction++;
            String str= amount+"Rs Deposited.\n";
            transactionHistory= transactionHistory.concat(str);
            System.out.println("Amount successfully deposited");
        }else{
            System.out.println("Limit amount is 20000");
        }

    }
    public void transfer(){
        System.out.println("Enter the account number to transfer: ");
        String accountRec=sc.next();

        System.out.println("Enter amount: ");
        float amount= sc.nextFloat();
        if(amount>accountBalance){
            System.out.println("Insufficient balance ");

        }
        else{
            if(amount<=20000){
                accountBalance-=amount;
                transaction++;
                String str= amount+"transferred to "+accountRec+"\n";
                transactionHistory=transactionHistory.concat(str);
                System.out.println("Amount successfully transferred");
            }else{
                System.out.println("Limit is Rs 20000");
            }
        }

    }
    public void balanceEnquiry(){
        System.out.println("Current account balance is Rs"+accountBalance+"\n");
    }
}

public class ATM_Machine {
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        BankAccount user= new BankAccount();
        System.out.println("_____________Welcome to ATM____________");
        System.out.println("Please insert the atm card___");
        boolean isValid=user.login();
        if(isValid){//user logged in successfully

            boolean flag=false;
            while(!flag){
                System.out.println("Enter your choice: ");
                System.out.println("\n1)Transaction History\n2)Withdraw\n3)Deposit\n4)Transfer\n5)Balance Enquiry\n6)Exit\n");
                int choice= sc.nextInt();

                switch(choice){
                    case 1 ->user.transactionHistory();
                    case 2 ->user.withdraw();
                    case 3 -> user.deposit();
                    case 4 -> user.transfer();
                    case 5 -> user.balanceEnquiry();
                    case 6 -> {
                        flag= true;
                        System.out.println("___Thank you___");
                    }
                    default -> System.out.println("Enter a valid choice.");

                }
            }
        }
    }
}