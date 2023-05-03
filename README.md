# todo
## APP的功能
该APP是一个任务清单APP，我们可以向其中加入自己待完成的任务，列出自己的任务清单，提醒自己完成任务。  
## 主要功能的实现思路
### 注册和登录功能
首先，通过EditText获取输入的账号和密码，并且输入两遍密码来确认密码的正确性。  
``` kotlin
val account1=mBinding.username1.text.toString()
val password1=mBinding.password1.text.toString()
val password2=mBinding.password2.text.toString()
```
接着，把账号和两次输入的密码传入我定义的register方法中
```kotlin
if(register(account1,password1,password2)){
                Toast.makeText(this,"注册成功，请返回登录界面登录",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"两次密码不一致，请重新输入",Toast.LENGTH_SHORT).show()
            }
```
register方法如下：
```kotlin
fun register(username:String,key1:String,key2:String):Boolean{
        val pref=getSharedPreferences("data",Context.MODE_PRIVATE)
        val account=username
        val password1=key1
        val password2=key2
        if (password1==password2){
            val editor=pref.edit()
            editor.putString("password",password1)
            editor.putString("account",account)
            editor.apply()
            return true
        }else{
            return false
        }

    }
```
当两次密码相同时，将账号和密码储存在本地的data文件中并返回true弹出注册成功的Toast
当两次密码不同时，直接返回false并弹出两次密码不一致的Toast  
之后就可以进行登录了
和注册相同，通过EditText获取输入的账号和密码，并将账号和密码传入login方法
```kotlin
val account=mBinding.username.text.toString()
            val password=mBinding.password.text.toString()
            //检验密码是否正确
            if(login(account,password)){
                Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show()
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"账号或密码错误",Toast.LENGTH_SHORT).show()
            }
```
login方法如下：
```kotlin
fun login(account:String,password:String):Boolean{
         val pref=getSharedPreferences("data",Context.MODE_PRIVATE)
         val user=pref.getString("account","")
         val pass=pref.getString("password","")
         return account==user&&password==pass
    }
```
从data文件中取出账号和密码的数据，判断传入的账号和密码是否与之相等
当相等时，返回true，弹出登录成功的Toast，并跳转到主界面
当不等时，返回false，提示账号或密码错误
### 创建任务功能
先向EditText中输入自己想添加的任务，然后按下加号按钮
用来确定这是第几个任务的变量position会加一，接着把position和editText存储在本
地文件中  
对于显示任务，这里用了RecyclerView
首先是RecyclerView的适配器
```kotlin
class RvAdapter(private val data:List<Data>): RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    data class Data(val assignment:String)

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val textView=view.findViewById<TextView>(R.id.tv_assignment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.text,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemData=data[position]
        holder.apply {
            textView.text=itemData.assignment
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
```
把实现RecyclerView的代码（包括layoutManager、adapter、下划线）包装成了
refresh方法，这样每当我们添加新的任务后，
只要调用这个方法，rv视图就可以更新
```kotlin
private fun refresh(){
        mBinding.rv.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=RvAdapter(viewModel.data)
            addItemDecoration(DividerItemDecoration(this@MainActivity,RecyclerView.VERTICAL))
        }
    }
```
从本地文件中取出数据，加入数组中，再传入RecyclerView的Adapter中，就显示出
来了任务列表
### 任务顺序的上下调整和左右滑删除
这里借鉴了学长课件里的代码来实现
运用了ItemTouchHelper

```kotlin
val helper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
                // 允许往上往下拖拽移动
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                // 允许左滑右滑删除
                val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // 开启长按拖拽，并且拖拽改变顺序
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                // 交换下位置
                Collections.swap(dataReserved, from, to)
                // notify 下
                mBinding.rv.adapter!!.notifyItemMoved(from, to)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // 被滑走时 这里把 item 删掉
                val pos = viewHolder.adapterPosition
                dataReserved.removeAt(pos)
                mBinding.rv.adapter!!.notifyItemRemoved(pos)
            }

        })
        helper.attachToRecyclerView(mBinding.rv)
```
## APP功能的展示  
注册
![20230503091603936](https://user-images.githubusercontent.com/119737732/235818420-5559bb12-0d04-4109-a61e-7bfb37e15afb.gif)  

登录
![20230503094114494](https://user-images.githubusercontent.com/119737732/235818861-1375c076-7af1-45bf-bda2-bcd0bb7fca62.gif)  

任务清单
![20230503094939871](https://user-images.githubusercontent.com/119737732/235819412-3cf25ed3-c903-49dc-a155-b9407a903498.gif)  

## 技术亮点
无。。  
## 心得体会
很多功能想要实现，但是力不从心，bug太多了：（  

## 待提升的地方(自己拷打自己)
+ 只能注册并登录一个账号
+ 一旦退出应用，列表中的数据就不见了
+ 当旋转屏幕后，再添加新的任务，本地文件中的数据会改变，但无法显示出来
