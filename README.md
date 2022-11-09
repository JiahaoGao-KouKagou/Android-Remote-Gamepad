# Android Remote Gamepad

This converts your phone to a gamepad to control computer games remotely, 
through a UDP client and server.

## 编码规则
0. 信号类型 ：1链接成功 / 2鼠标移动 / 4鼠标滚轮 / 8键盘
1. 
    - 键盘：对应键码
2. 
    - 键盘：对应键码
3. 
    - 键盘：1按下 / 2弹起
    

## Andriod端

### 类设计

#### 触发类
##### 属性
- 键值
- 状态：1按下 / 2弹起
##### 方法
- 发送请求（键值， 状态）

#### 摇杆类